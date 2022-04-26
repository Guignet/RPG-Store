package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.*;
import com.project.rpgstoreback.models.*;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.ProductRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("UsableRestController")
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/products/usables")
public class UsableController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TagRepository tagRepository;

    @GetMapping()
    public ResponseEntity<?> fetchAll() {
        List<ResponseUsableDTO> response = new ArrayList<>();

        this.productRepository.findAll().forEach(
                product -> {
                    if (product instanceof Usable){
                        ResponseUsableDTO prodDto = getUsablesDTO(product);
                        response.add(prodDto);
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOneById(@PathVariable("id") Long idProd) {

        Usable usable = (Usable) this.productRepository.findById(idProd).orElseThrow(() -> new UsernameNotFoundException("Product Usable not found"));
        ResponseUsableDTO dto = fetchUsableDto(usable);

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateUpdateUsableDTO dto ){

        Usable newUsable = new Usable();

        newUsable.setTitle(dto.getTitle());
        newUsable.setDescription(dto.getDescription());
        newUsable.setQuantity(dto.getQuantity());
        newUsable.setPrice(dto.getPrice());
        newUsable.setPictures(dto.getPictures());
        newUsable.setDurability(dto.getDurability());

        Account creator = accountRepository.findById(dto.getCreator()).orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
        newUsable.setCreator(creator);

        Iterable<Tag> tags = tagRepository.findAllById(dto.getListTags());

        newUsable.setListTags( (List) tags);

        this.productRepository.save(newUsable);

        return fetchAll();
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody CreateUpdateUsableDTO dto){

        Usable updateUsable = (Usable) this.productRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("Product Usable not found"));

//        updateUsable.setId(dto.getId());
        updateUsable.setTitle(dto.getTitle());
        updateUsable.setDescription(dto.getDescription());
        updateUsable.setQuantity(dto.getQuantity());
        updateUsable.setPrice(dto.getPrice());
        updateUsable.setPictures(dto.getPictures());
        updateUsable.setDurability(dto.getDurability());

        Account creator = accountRepository.findById(dto.getCreator()).orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
        updateUsable.setCreator(creator);

        Iterable<Tag> tags = tagRepository.findAllById(dto.getListTags());

        updateUsable.setListTags( (List) tags);

        this.productRepository.save(updateUsable);

        return fetchOneById(updateUsable.getId());
//        return ResponseEntity
//                .ok()
//                .body("Produit usable : ' " + updateUsable.getTitle()+ " ' à été update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idUsable){

        Usable deleteUs = (Usable) this.productRepository.findById(idUsable).orElseThrow(() -> new UsernameNotFoundException("Product Usable not found"));

        //supprime du panier
        this.accountRepository.findAll().forEach(
                account -> {
                    account.removeProduct(deleteUs);
                }
        );
        //vide la list Tag
        deleteUs.setListTags(new ArrayList<>());
        this.productRepository.save(deleteUs);

        this.productRepository.deleteById(idUsable);

        return ResponseEntity
                .ok()
                .body("Produit Usable: " + deleteUs.getTitle() + ", à été supprimé");
    }

    public ResponseUsableDTO getUsablesDTO(Product product){
        ResponseUsableDTO prodDto = new ResponseUsableDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getPictures(),
                ((Usable) product).getDurability()
        );

        //SET TAG PRODUCT
        List<TagDTO> taglist = new ArrayList<>();
        product.getListTags().forEach(
                t -> taglist.add(new TagDTO(t.getId(),t.getName(),t.getDescription()))
        );
        prodDto.setListTags(taglist);

        //SET CREATOR PRODUCT
        List<Long> idsRole = new ArrayList<>();
        product.getCreator().getRoleList()
                .forEach(
                        e -> idsRole.add(e.getId())
                );


        ResponseAccountProductDTO creator = new ResponseAccountProductDTO(
                product.getCreator().getId(),
                product.getCreator().getFirstName(),
                product.getCreator().getLastName(),
                product.getCreator().getUsername(),
                product.getCreator().getPassword(),
                product.getCreator().getEmail(),
                product.getCreator().getRegistrationDate(),
                product.getCreator().isActive(),
                idsRole
        );

        prodDto.setCreator(creator);
        return prodDto;
    }

    public ResponseUsableDTO fetchUsableDto(Usable usable){
        ResponseUsableDTO dto = new ResponseUsableDTO();

        dto.setId(usable.getId());
        dto.setTitle(usable.getTitle());
        dto.setDescription(usable.getDescription());
        dto.setQuantity(usable.getQuantity());
        dto.setPrice(usable.getPrice());
        dto.setPictures(usable.getPictures());
        dto.setDurability(usable.getDurability());

        List<TagDTO> tags = new ArrayList<>();
        usable.getListTags().forEach(
                t -> tags.add(new TagDTO(t.getId(),t.getName(),t.getDescription()))
        );
        dto.setListTags(tags);

        dto.setCreator(fetchAccountDto(usable.getCreator()));

        return dto;
    }

    public ResponseAccountProductDTO fetchAccountDto(Account account){
        ResponseAccountProductDTO dto = new ResponseAccountProductDTO();
        dto.setId(account.getId());
        dto.setFirstName(account.getFirstName());
        dto.setLastName(account.getLastName());
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setEmail(account.getEmail());
        dto.setRegistrationDate(account.getRegistrationDate());
        dto.setActive(account.isActive());


        List<Long> idsRole = new ArrayList<>();
        account.getRoleList()
                .forEach(
                        e -> idsRole.add(e.getId())
                );
        dto.setRoleList(idsRole);

        return dto;
    }
}
