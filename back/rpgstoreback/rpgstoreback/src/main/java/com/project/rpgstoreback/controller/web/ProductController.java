package com.project.rpgstoreback.controller.web;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Product;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.ProductRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/products")
    public String getAll(Model model){
        Iterator<Product> iterator = productRepository.findAll().iterator();
        List<Product> products = new ArrayList<>();
        while(iterator.hasNext()){
            products.add(iterator.next());
        }
        model.addAttribute("Products",products);
        return "products";
    }

    @PostMapping("/remove-product")
    public String remove(@RequestParam("productId") Long productId){
        Product todelete = productRepository.findById(productId).orElseThrow();
        Iterator<Account> iteAccounts = accountRepository.findAll().iterator();
        List<Account> accounts = new ArrayList<>();
        while(iteAccounts.hasNext()){
            accounts.add(iteAccounts.next());
        }
        for(Account account:accounts){
            account.removeProduct(todelete);
            accountRepository.save(account);
        }
        productRepository.delete(todelete);
        return "redirect:/auth/products";
    }

}
