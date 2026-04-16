package com.app.ecom.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock_quantity;
    private String description;
    private String imageUrl;
    private Boolean active;

}
