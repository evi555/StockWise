package com.app.ecom.dto;

import com.app.ecom.models.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartResponse {
    private Product product;
    private Integer quantity;
    private Long id;
    private BigDecimal price;



}
