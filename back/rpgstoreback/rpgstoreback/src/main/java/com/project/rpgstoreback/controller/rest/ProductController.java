package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.*;
import com.project.rpgstoreback.models.Armor;
import com.project.rpgstoreback.models.Product;
import com.project.rpgstoreback.models.Usable;
import com.project.rpgstoreback.models.Weapon;
import com.project.rpgstoreback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<?> fetchAll() {

        List<ResponseProductDTO> response = new ArrayList<>();

        this.productRepository.findAll().forEach(
                product -> {
                    if (product instanceof Weapon){
                        ResponseProductDTO prodDto = getWeaponsDto(product);
                        response.add(prodDto);
                    }
                    if (product instanceof Armor){
                        ResponseProductDTO prodDto = getArmorsDTO(product);
                        response.add(prodDto);
                    }
                    if (product instanceof Usable){
                        ResponseProductDTO prodDto = getUsablesDTO(product);
                        response.add(prodDto);
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(response);
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

}
