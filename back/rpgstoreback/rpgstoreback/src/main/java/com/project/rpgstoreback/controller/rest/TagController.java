package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.*;
import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Armor;
import com.project.rpgstoreback.models.Tag;
import com.project.rpgstoreback.repository.ProductRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/tags")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<?> fetchAll() {
        List<TagDTO> response = new ArrayList<>();

        this.tagRepository.findAll().forEach(
                tag -> {
                    response.add(new TagDTO(
                            tag.getId(),
                            tag.getName(),
                            tag.getDescription()
                    ));
                }
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOneById(@PathVariable("id") Long idTag) {
        Tag tag = this.tagRepository.findById(idTag).orElseThrow(() -> new UsernameNotFoundException("Tag not found"));

        ResponseTagDTO response = new ResponseTagDTO(
                tag.getId(),
                tag.getName(),
                tag.getDescription()
        );

        response.setListProd(productsWithTag(idTag));

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TagDTO dto ){

        Tag newTag = new Tag();

        newTag.setName(dto.getName());
        newTag.setDescription(dto.getDescription());

        this.tagRepository.save(newTag);

        return fetchAll();
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody TagDTO dto ){

        Tag newTag =  this.tagRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("Tag not found"));
        // SANS LIST PROD
        newTag.setId(dto.getId());
        newTag.setName(dto.getName());
        newTag.setDescription(dto.getDescription());

        this.tagRepository.save(newTag);

        return fetchAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idTag) {
        Tag deleteTag = this.tagRepository.findById(idTag).orElseThrow(() -> new UsernameNotFoundException("Tag not found"));


        this.productRepository.findAll().forEach(
                product -> {
                    product.getListTags().forEach(
                            t -> {
                                if (t.getId() == idTag){
                                    product.removeTag(deleteTag);
                                }
                            });
                });

        this.tagRepository.deleteById(idTag);

        return ResponseEntity
                .ok()
                .body("Tag: " + deleteTag.getName() + ", à été supprimé");
    }

    public List<ResponseTagProductDTO> productsWithTag(Long idTag){
        List<ResponseTagProductDTO> productsWithTag = new ArrayList<>();
        this.productRepository.findAll().forEach(
                product -> {
                    product.getListTags().forEach(
                            t -> {
                                if (t.getId() == idTag){
                                    productsWithTag.add(
                                            new ResponseTagProductDTO(
                                                    product.getId(),
                                                    product.getTitle(),
                                                    product.getDescription(),
                                                    product.getCreator().getUsername(),
                                                    product.getPictures()
                                            )
                                    );
                                }
                            });
                });
        return productsWithTag;
    }
}
