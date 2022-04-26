package com.project.rpgstoreback.controller.rest.modelDTO;

import java.util.List;

public class ResponseTagDTO {

    private Long id;
    private String name;
    private String description;
    private List<ResponseTagProductDTO> listProd;

    public ResponseTagDTO() {}

    public ResponseTagDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ResponseTagProductDTO> getListProd() {
        return listProd;
    }

    public void setListProd(List<ResponseTagProductDTO> listProd) {
        this.listProd = listProd;
    }
}
