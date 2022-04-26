package com.project.rpgstoreback.models;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Armor extends Product{
    @NotNull
    private int resistance;

    public Armor() {}

    public Armor(String title, String description, int quantity, long price, Long seller, List<String> pictures, List<Tag> listTags, int resistance) {
        super(title, description, quantity, price, seller, pictures, listTags);
        this.resistance = resistance;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
