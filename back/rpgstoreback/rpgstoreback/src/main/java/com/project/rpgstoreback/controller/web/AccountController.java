package com.project.rpgstoreback.controller.web;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.form.AccountForm;
import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
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

    @PostMapping("/remove-account")
    public String remove(@RequestParam("accountId") Long accountId){
        Account todelete = accountRepository.findById(accountId).orElseThrow();
        todelete.setListProducts(null);
        accountRepository.delete(todelete);
        return "redirect:/auth/accounts";
    }

    @PostMapping("/account-details")
    public String details(@RequestParam("accountId") Long accountId,Model model){
        Account account = accountRepository.findById(accountId).orElseThrow();
        model.addAttribute("accountDetails",account);
        return "account-details";
    }

    @PostMapping("/activate-account")
    public String activate(@RequestParam("accountId") Long accountId){
        Account toActivate = accountRepository.findById(accountId).orElseThrow();
        toActivate.setActive(true);
        accountRepository.save(toActivate);
        return "redirect:/auth/accounts";
    }
    @PostMapping("/deactivate-account")
    public String deactivate(@RequestParam("accountId") Long accountId){
        Account toActivate = accountRepository.findById(accountId).orElseThrow();
        toActivate.setActive(false);
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
    @GetMapping("/update-account")
    public String updateForm(@RequestParam("accountId")Long accountId, Model model){
        Account account = accountRepository.findById(accountId).orElseThrow();

        Iterator<Role> iterator = roleRepository.findAll().iterator();
        List<Role> roles = new ArrayList<>();

        while(iterator.hasNext()){
            roles.add(iterator.next());
        }
        AccountForm accountForm = new AccountForm();
        accountForm.setId(account.getId());
        accountForm.setFirstName(account.getFirstName());
        accountForm.setLastName(account.getLastName());
        accountForm.setUsername(account.getUsername());
        accountForm.setEmail(account.getEmail());

        model.addAttribute("accountForm",accountForm);

        model.addAttribute("roles",roles);
        return "update-account";
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
                    accountForm.getUsername(), passwordEncoder.encode(accountForm.getPassword()), accountForm.getEmail(), LocalDate.now(), false,roles);
            accountRepository.save(tosave);

        }
        return "redirect:/auth/accounts";
    }
    @PostMapping("/update-account")
    public String update(@ModelAttribute AccountForm accountForm,Model model){
        if(accountRepository.existsByUsername(accountForm.getUsername())){
            return  "redirect:/auth/accounts";
        }else{
            List<Role> roles = new ArrayList<>();
            Iterator<Role> ite = roleRepository.findAllById(accountForm.getRoles()).iterator();
            while(ite.hasNext()){
                roles.add(ite.next());
            }
            Account toupdate = accountRepository.findById(accountForm.getId()).orElseThrow();
            toupdate.setId(accountForm.getId());
            toupdate.setFirstName(accountForm.getFirstName());
            toupdate.setLastName(accountForm.getLastName());
            toupdate.setUsername(accountForm.getUsername());
            toupdate.setEmail(accountForm.getEmail());

            toupdate.setRoleList(roles);
            accountRepository.save(toupdate);

        }
        return "redirect:/auth/accounts";
    }
}
