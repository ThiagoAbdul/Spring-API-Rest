package com.abdul.hellospring.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private Float price;
    private Integer quantity;
    private String note;
    
}
