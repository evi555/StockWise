package com.ecommerce.product.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRequest {
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock_quantity;
    private String description;
    private String imageUrl;

}
