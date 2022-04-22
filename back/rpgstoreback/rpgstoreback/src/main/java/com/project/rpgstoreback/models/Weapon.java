package com.project.rpgstoreback.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Weapon extends Product{
    @NotNull
    private int damage;

    public Weapon() {}

    public Weapon(String title, String description, int quantity, long price, Account seller, List<String> pictures, List<Tag> listTags, int damage) {
        super(title, description, quantity, price, seller, pictures, listTags);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
