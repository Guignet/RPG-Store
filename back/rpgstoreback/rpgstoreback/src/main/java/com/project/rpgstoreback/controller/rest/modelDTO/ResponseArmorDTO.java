package com.project.rpgstoreback.controller.rest.modelDTO;

import java.util.List;

public class ResponseArmorDTO extends ResponseProductDTO{


    private int resistance;

    public ResponseArmorDTO(){}

    public ResponseArmorDTO(Long id, String title, String description, int quantity, long price, List<String> pictures, int resistance) {
        super(id, title, description, quantity, price, pictures);
        this.resistance = resistance;
    }

    public ResponseArmorDTO(Long id, String title, String description, int quantity, long price, ResponseAccountProductDTO creator, List<String> pictures, List<TagDTO> listTags, int resistance) {
        super(id, title, description, quantity, price, creator, pictures, listTags);
        this.resistance = resistance;

    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
