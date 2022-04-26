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

@RestController("WeaponRestController")
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/products/weapons")
public class WeaponController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TagRepository tagRepository;

    @GetMapping()
    public ResponseEntity<?> fetchAll() {
        List<ResponseWeaponDTO> response = new ArrayList<>();

        this.productRepository.findAll().forEach(
                product -> {
                    if (product instanceof Weapon){
                        ResponseWeaponDTO prodDto = getWeaponsDto(product);
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

        Weapon weapon = (Weapon) this.productRepository.findById(idProd).orElseThrow(() -> new UsernameNotFoundException("Product Weapon not found"));
        ResponseWeaponDTO dto = fetchWeaponDto(weapon);

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateUpdateWeaponDTO dto ){

        Weapon newWeapon = new Weapon();

        newWeapon.setTitle(dto.getTitle());
        newWeapon.setDescription(dto.getDescription());
        newWeapon.setQuantity(dto.getQuantity());
        newWeapon.setPrice(dto.getPrice());
        newWeapon.setPictures(dto.getPictures());
        newWeapon.setDamage(dto.getDamage());

        Account creator = accountRepository.findById(dto.getCreator()).orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
        newWeapon.setCreator(creator);

        Iterable<Tag> tags = tagRepository.findAllById(dto.getListTags());
        newWeapon.setListTags( (List) tags);

        this.productRepository.save(newWeapon);

        return fetchAll();
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody CreateUpdateWeaponDTO dto){

        Weapon updateWeapon = (Weapon) this.productRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("Product Weapon not found"));

//        updateUsable.setId(dto.getId());
        updateWeapon.setTitle(dto.getTitle());
        updateWeapon.setDescription(dto.getDescription());
        updateWeapon.setQuantity(dto.getQuantity());
        updateWeapon.setPrice(dto.getPrice());
        updateWeapon.setPictures(dto.getPictures());
        updateWeapon.setDamage(dto.getDamage());

        Account creator = accountRepository.findById(dto.getCreator()).orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
        updateWeapon.setCreator(creator);

        Iterable<Tag> tags = tagRepository.findAllById(dto.getListTags());

        updateWeapon.setListTags( (List) tags);

        this.productRepository.save(updateWeapon);

        return fetchOneById(updateWeapon.getId());
//        return ResponseEntity
//                .ok()
//                .body("Produit weapon : ' " + updateWeapon.getTitle()+ " ' à été update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idWeapon){

        Weapon deleteWe = (Weapon) this.productRepository.findById(idWeapon).orElseThrow(() -> new UsernameNotFoundException("Product Weapon not found"));

        //supprime du panier
        this.accountRepository.findAll().forEach(
                account -> {
                    account.removeProduct(deleteWe);
                }
        );
        //vide la list Tag
        deleteWe.setListTags(new ArrayList<>());
        this.productRepository.save(deleteWe);
//
        this.productRepository.deleteById(idWeapon);

        return ResponseEntity
                .ok()
                .body("Produit Weapon: " + deleteWe.getTitle() + ", à été supprimé");
    }


    public ResponseWeaponDTO fetchWeaponDto(Weapon weapon){
        ResponseWeaponDTO dto = new ResponseWeaponDTO();

        dto.setId(weapon.getId());
        dto.setTitle(weapon.getTitle());
        dto.setDescription(weapon.getDescription());
        dto.setQuantity(weapon.getQuantity());
        dto.setPrice(weapon.getPrice());
        dto.setPictures(weapon.getPictures());
        dto.setDamage(weapon.getDamage());

        List<TagDTO> tags = new ArrayList<>();
        weapon.getListTags().forEach(
                t -> tags.add(new TagDTO(t.getId(),t.getTitle(),t.getDescription()))
        );
        dto.setListTags(tags);

        dto.setCreator(fetchAccountDto(weapon.getCreator()));

        return dto;
    }

    public ResponseWeaponDTO getWeaponsDto(Product product){
        ResponseWeaponDTO prodDto = new ResponseWeaponDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getPictures(),
                ((Weapon) product).getDamage()
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
