package com.project.rpgstoreback.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Usable extends Product{
    @NotNull
    private int durability = 0;// 0 = infini

    public Usable() {}

    public Usable(String title, String description, int quantity, long price, Long seller, List<String> pictures, List<Tag> listTags, int durability) {
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
