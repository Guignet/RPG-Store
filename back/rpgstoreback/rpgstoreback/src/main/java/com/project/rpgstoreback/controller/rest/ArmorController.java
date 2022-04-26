package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.*;
import com.project.rpgstoreback.models.*;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.ProductRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("ArmorRestController")
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/products/armors")
public class ArmorController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TagRepository tagRepository;

    @GetMapping()
    public ResponseEntity<?> fetchAll() {
        List<ResponseArmorDTO> response = new ArrayList<>();

        this.productRepository.findAll().forEach(
                product -> {
                    if (product instanceof Armor){
                        ResponseArmorDTO prodDto = getArmorsDTO(product);
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

        Armor armor = (Armor) this.productRepository.findById(idProd).orElseThrow(() -> new UsernameNotFoundException("Product Armor not found"));
        ResponseArmorDTO dto = fetchArmorDto(armor);

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateUpdateArmorDTO dto ){

        Armor newArmor = new Armor();

        newArmor.setTitle(dto.getTitle());
        newArmor.setDescription(dto.getDescription());
        newArmor.setQuantity(dto.getQuantity());
        newArmor.setPrice(dto.getPrice());
        newArmor.setPictures(dto.getPictures());
        newArmor.setResistance(dto.getResistance());

        Account creator = accountRepository.findById(dto.getCreator()).orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
        newArmor.setCreator(creator);

        Iterable<Tag> tags = tagRepository.findAllById(dto.getListTags());

        newArmor.setListTags( (List) tags);

        this.productRepository.save(newArmor);

        return fetchAll();
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody CreateUpdateArmorDTO dto){

        Armor updateArmor = (Armor) this.productRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("Product Armor not found"));

//        updateUsable.setId(dto.getId());
        updateArmor.setTitle(dto.getTitle());
        updateArmor.setDescription(dto.getDescription());
        updateArmor.setQuantity(dto.getQuantity());
        updateArmor.setPrice(dto.getPrice());
        updateArmor.setPictures(dto.getPictures());
        updateArmor.setResistance(dto.getResistance());

        Account creator = accountRepository.findById(dto.getCreator()).orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
        updateArmor.setCreator(creator);

        Iterable<Tag> tags = tagRepository.findAllById(dto.getListTags());

        updateArmor.setListTags( (List) tags);

        this.productRepository.save(updateArmor);

        return fetchOneById(updateArmor.getId());
//        return ResponseEntity
//                .ok()
//                .body("Produit Armor : ' " + updateArmor.getTitle()+ " ' à été update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idArmor){

        Armor deleteAr = (Armor) this.productRepository.findById(idArmor).orElseThrow(() -> new UsernameNotFoundException("Product Armor not found"));

        //supprime du panier
        this.accountRepository.findAll().forEach(
                account -> {
                    account.removeProduct(deleteAr);
                }
        );
        //vide la list Tag
        deleteAr.setListTags(new ArrayList<>());
        this.productRepository.save(deleteAr);

        this.productRepository.deleteById(idArmor);

        return ResponseEntity
                .ok()
                .body("Produit Armor: " + deleteAr.getTitle() + ", à été supprimé");
    }


    public ResponseArmorDTO getArmorsDTO(Product product){
        ResponseArmorDTO prodDto = new ResponseArmorDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getPictures(),
                ((Armor) product).getResistance()
        );

        //SET TAG PRODUCT
        List<TagDTO> taglist = new ArrayList<>();
        product.getListTags().forEach(
                t -> taglist.add(new TagDTO(t.getId(),t.getTitle(),t.getDescription()))
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

    public ResponseArmorDTO fetchArmorDto(Armor armor){
        ResponseArmorDTO dto = new ResponseArmorDTO();

        dto.setId(armor.getId());
        dto.setTitle(armor.getTitle());
        dto.setDescription(armor.getDescription());
        dto.setQuantity(armor.getQuantity());
        dto.setPrice(armor.getPrice());
        dto.setPictures(armor.getPictures());
        dto.setResistance(armor.getResistance());

        List<TagDTO> tags = new ArrayList<>();
        armor.getListTags().forEach(
                t -> tags.add(new TagDTO(t.getId(),t.getTitle(),t.getDescription()))
        );
        dto.setListTags(tags);

        dto.setCreator(fetchAccountDto(armor.getCreator()));

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
