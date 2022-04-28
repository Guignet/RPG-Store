package com.project.rpgstoreback.controller.web;

import com.project.rpgstoreback.models.*;
import com.project.rpgstoreback.models.form.AccountForm;
import com.project.rpgstoreback.models.form.ProductForm;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.ProductRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/create-product")
    public String createForm(Model model){
        Iterator<Tag> iterator = tagRepository.findAll().iterator();
        List<Tag> tags = new ArrayList<>();

        while(iterator.hasNext()){
            tags.add(iterator.next());
        }
        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm",productForm);

        model.addAttribute("tags",tags);
        return "create-product";
    }
    @PostMapping("/create-product")
    public String create(@ModelAttribute ProductForm productForm){
        if(productRepository.existsByTitle(productForm.getTitle())){
            return  "redirect:/auth/create-product";
        }else{
            String currentUserName = null;
            Account current = null;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                 currentUserName = authentication.getName();
            }
            if(currentUserName!=null){
                current = accountRepository.findByUsername(currentUserName).orElseThrow();
            }
            List<Tag> tags = new ArrayList<>();
            Iterator<Tag> ite = tagRepository.findAllById(productForm.getListTags()).iterator();
            while(ite.hasNext()){
                tags.add(ite.next());
            }
            switch(productForm.getType()){
                case 1:
                    Product weapontosave = new Weapon(productForm.getTitle(),productForm.getDescription(),productForm.getQuantity(),
                            productForm.getPrice(),current,productForm.getPictures(),tags,productForm.getValForm());
                    productRepository.save(weapontosave);
                    break;
                case 2:
                    Product armortosave = new Armor(productForm.getTitle(),productForm.getDescription(),productForm.getQuantity(),
                            productForm.getPrice(),current,productForm.getPictures(),tags,productForm.getValForm());
                    productRepository.save(armortosave);
                    break;
                case 3:
                    Product usabletosave = new Usable(productForm.getTitle(),productForm.getDescription(),productForm.getQuantity(),
                            productForm.getPrice(),current,productForm.getPictures(),tags,productForm.getValForm());
                    productRepository.save(usabletosave);
                    break;
                default:
                    return "redirect:/auth/create-product";


            }


        }
        return "redirect:/auth/products";
    }

    @PostMapping("/product-details")
    public String details(@RequestParam("productId") Long productId,Model model){
        Product product = productRepository.findById(productId).orElseThrow();
        model.addAttribute("productDetails",product);
        return "product-details";
    }

}
