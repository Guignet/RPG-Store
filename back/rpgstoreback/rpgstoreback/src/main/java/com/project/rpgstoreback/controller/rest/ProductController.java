package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.CreateAccount;
import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Product;
import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<?> fetchAll() {
        return ResponseEntity
                .ok()
                .body(this.productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOneById(@PathVariable("id") Long idProd) {
        Product prod = this.productRepository.findById(idProd).orElseThrow(() -> new UsernameNotFoundException("Product not found"));

        return ResponseEntity
                .ok()
                .body(prod);
    }

}
