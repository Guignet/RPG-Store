package com.project.rpgstoreback.controller.web;

import com.project.rpgstoreback.models.Product;
import com.project.rpgstoreback.models.Tag;
import com.project.rpgstoreback.models.form.TagForm;
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
public class TagController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tags")
    public String getAll(Model model){
        Iterator<Tag> iterator = tagRepository.findAll().iterator();
        List<Tag> tag = new ArrayList<>();
        while(iterator.hasNext()){
            tag.add(iterator.next());
        }
        model.addAttribute("Tags",tag);
        return "tags";
    }

    @PostMapping("/remove-tag")
    public String remove(@RequestParam("tagId") Long tagId){
        Tag todelete = tagRepository.findById(tagId).orElseThrow();
        Iterator<Product> iterator = productRepository.findAll().iterator();
        List<Product> products = new ArrayList<>();
        while(iterator.hasNext()){
            products.add(iterator.next());
        }
        for(Product product : products){
            product.removeTag(todelete);
            productRepository.save(product);
        }
        tagRepository.delete(todelete);
        return "redirect:/auth/tags";
    }

    @GetMapping("/create-tag")
    public String createForm(Model model){

        TagForm tagForm = new TagForm();
        model.addAttribute("tagForm",tagForm);

        return "create-tag";
    }
    @PostMapping("/create-tag")
    public String create(@ModelAttribute TagForm tagForm){
        if(tagRepository.existsByTitle(tagForm.getTitle())){
            return  "redirect:/auth/create-account";
        }else{
            Tag tosave = new Tag(tagForm.getTitle(), tagForm.getDescription());
            tagRepository.save(tosave);

        }
        return "redirect:/auth/tags";
    }


}
