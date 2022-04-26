package com.project.rpgstoreback.controller.rest.modelDTO;

import com.project.rpgstoreback.models.Account;

import java.util.ArrayList;
import java.util.List;

public class ResponseWeaponDTO extends ResponseProductDTO{


    private int damage;

    public ResponseWeaponDTO(){}

    public ResponseWeaponDTO(Long id, String title, String description, int quantity, long price, List<String> pictures, int damage) {
        super(id, title, description, quantity, price, pictures);
        this.damage = damage;
    }

    public ResponseWeaponDTO(Long id, String title, String description, int quantity, long price, ResponseAccountProductDto creator, List<String> pictures, List<TagDTO> listTags, int damage) {
        super(id, title, description, quantity, price, creator, pictures, listTags);
        this.damage = damage;

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
