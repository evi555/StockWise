package com.ecommerce.order.dto;

//import com.ecommerce.order.models.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartResponse {
    private Long productId;
    private Integer quantity;
    private Long id;
    private BigDecimal price;



}
