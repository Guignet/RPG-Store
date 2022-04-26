package com.project.rpgstoreback.controller.rest.modelDTO;

import java.util.List;

public class ResponseUsableDTO extends ResponseProductDTO{


    private int durability;

    public ResponseUsableDTO(){}

    public ResponseUsableDTO(Long id, String title, String description, int quantity, long price, List<String> pictures, int durability) {
        super(id, title, description, quantity, price, pictures);
        this.durability = durability;
    }

    public ResponseUsableDTO(Long id, String title, String description, int quantity, long price, ResponseAccountProductDto creator, List<String> pictures, List<TagDTO> listTags, int durability) {
        super(id, title, description, quantity, price, creator, pictures, listTags);
        this.durability = durability;

    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
