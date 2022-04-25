package com.project.rpgstoreback.controller.web;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.AccountForm;
import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/accounts")
    public String getAll(Model model){
        Iterator<Account> iteAccounts = accountRepository.findAll().iterator();
        List<Account> accounts = new ArrayList<>();
        while(iteAccounts.hasNext()){
            accounts.add(iteAccounts.next());
        }
        model.addAttribute("Accounts",accounts);
        return "accounts";
    }

    @PostMapping("/removeAccount")
    public String remove(@RequestParam("accountId") Long accountId){
        accountRepository.delete(accountRepository.findById(accountId).orElseThrow());
        return "redirect:/auth/accounts";
    }

    @PostMapping("/activateAccount")
    public String activate(@RequestParam("accountId") Long accountId){
        Account toActivate = accountRepository.findById(accountId).orElseThrow();
        toActivate.setActive(true);
        accountRepository.save(toActivate);
        return "redirect:/auth/accounts";
    }

    @GetMapping("/create-account")
    public String createForm(Model model){
        Iterator<Role> iterator = roleRepository.findAll().iterator();
        List<Role> roles = new ArrayList<>();

        while(iterator.hasNext()){
            roles.add(iterator.next());
        }
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm",accountForm);

        model.addAttribute("roles",roles);
        return "create-account";
    }
    @PostMapping("/create-account")
    public String create(@ModelAttribute AccountForm accountForm){
        if(accountRepository.existsByUsername(accountForm.getUsername())){
            return  "redirect:/auth/create-account";
        }else{
            List<Role> roles = new ArrayList<>();
                    Iterator<Role> ite = roleRepository.findAllById(accountForm.getRoles()).iterator();
                    while(ite.hasNext()){
                        roles.add(ite.next());
                    }
            Account tosave = new Account(accountForm.getFirstName(), accountForm.getLastName(),
                    accountForm.getUsername(), accountForm.getPassword(), accountForm.getEmail(), LocalDate.now(), false,roles);
            accountRepository.save(tosave);
        }
        return "redirect:/auth/accounts";
    }
}
