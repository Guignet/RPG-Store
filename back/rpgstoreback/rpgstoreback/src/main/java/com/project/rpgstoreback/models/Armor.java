package com.project.rpgstoreback.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Armor extends Product{

    private int resistance;

    public Armor() {}

    public Armor(String title, String description, int quantity, Account seller, List<String> pictures, List<Tag> listTags, int resistance) {
        super(title, description, quantity, seller, pictures, listTags);
        this.resistance = resistance;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
