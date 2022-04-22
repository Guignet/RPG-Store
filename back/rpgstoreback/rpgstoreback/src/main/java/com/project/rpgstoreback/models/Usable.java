package com.project.rpgstoreback.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Usable extends Product{

    private int durability;

    public Usable() {}

    public Usable(String title, String description, int quantity, long price, Account seller, List<String> pictures, List<Tag> listTags, int durability) {
        super(title, description, quantity, price, seller, pictures, listTags);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
