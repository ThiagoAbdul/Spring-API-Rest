package com.abdul.hellospring.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {

    private Integer id;
    private String name;
    private Float price;
    private Integer quantity;
    private String note;

    public ProductDTO(String name, Float price, Integer quantity, String note) {
        super();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
    }

    public ProductDTO(String name, Integer quantity, Float price){
        this(name, price, quantity, null);
    }

}
    

