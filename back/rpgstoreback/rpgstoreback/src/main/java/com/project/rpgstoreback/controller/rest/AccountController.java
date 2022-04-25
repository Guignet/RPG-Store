package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.CreateAccount;
import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(value = "*")
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/all")
    public ResponseEntity<?> fetchAll() {
//        List<Account> accountList = this.accountRepository.findAll();

        return ResponseEntity
                .ok()
                .body(this.accountRepository.findAll());
    }

    @GetMapping("/admin")
    public ResponseEntity<?> fetchAllAdmin() {
        List<Account> adminList = new ArrayList<>();

        this.accountRepository.findAll().forEach(
                account -> {
                    if (account.getRoleList().stream().anyMatch(role -> role.getName().name() == "ADMIN")){
                        adminList.add(account);
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(adminList);
    }

    @GetMapping("/seller")
    public ResponseEntity<?> fetchAllSeller() {
        List<Account> adminList = new ArrayList<>();

        this.accountRepository.findAll().forEach(
                account -> {
                    if (account.getRoleList().stream().anyMatch(role -> role.getName().name() == "SELLER")){
                        adminList.add(account);
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(adminList);
    }

    @GetMapping("/user")
    public ResponseEntity<?> fetchAllUser() {
        List<Account> adminList = new ArrayList<>();

        this.accountRepository.findAll().forEach(
                account -> {
                    if (account.getRoleList().stream().anyMatch(role -> role.getName().name() == "USER")){
                        adminList.add(account);
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(adminList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOneById(@PathVariable("id") Long idAccount) {
        Account account = this.accountRepository.findById(idAccount).orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        return ResponseEntity
                .ok()
                .body(account);
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody CreateAccount dto ){

        Account newAccount = new Account();

        newAccount.setFirstName(dto.getFirstName());
        newAccount.setLastName(dto.getLastName());
        newAccount.setUsername(dto.getUsername());
        newAccount.setPassword(encoder.encode(dto.getPassword())); // hash maintenant ou fait avant ?
        newAccount.setEmail(dto.getEmail());
        newAccount.setRegistrationDate(dto.getRegistrationDate()); // verif bien envoyé


        Iterable<Role> roles = this.roleRepository.findAllById(dto.getRoleList());

        newAccount.setRoleList((List) roles);

        this.accountRepository.save(newAccount);

        return fetchAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CreateAccount dto) {

       Account updateAc = this.accountRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("Account not found"));


//        updateAc.setId(dto.getId()); // set Id en plus
        updateAc.setFirstName(dto.getFirstName());
        updateAc.setLastName(dto.getLastName());
        updateAc.setUsername(dto.getUsername());
        updateAc.setPassword(encoder.encode(dto.getPassword())); // hash maintenant ou fait avant ?
        updateAc.setEmail(dto.getEmail());
        updateAc.setRegistrationDate(dto.getRegistrationDate()); // verif bien envoyé


        Iterable<Role> roles = this.roleRepository.findAllById(dto.getRoleList());

        updateAc.setRoleList((List) roles);

        this.accountRepository.save(updateAc);

        return ResponseEntity
                .ok()
                .body("Compte de ' " + updateAc.getUsername()+ " ' à été update");
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idAccount){

        String deleteAc = this.accountRepository.findById(idAccount).get().getUsername();

        this.accountRepository.deleteById(idAccount);

        return ResponseEntity
                .ok()
                .body("Compte de ' " + deleteAc + " ' à été supprimé");
    }
}
