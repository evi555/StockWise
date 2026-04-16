package com.app.ecom.controller;

import com.app.ecom.dto.CartRequest;
import com.app.ecom.models.CartItem;
import com.app.ecom.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String user_id, @RequestBody CartRequest cartRequest){
        Boolean response = cartItemService.addToBasket(user_id,cartRequest);
        if(response){
          return   ResponseEntity.ok("Product added to Cart");
        }
        else {
         return    ResponseEntity.badRequest().body("Product out of Stock or User was not found or Product was not found");
        }
    }
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> removeFromCart(@RequestHeader("X-User-ID") String user_id, @PathVariable Long productId){
        Boolean result = cartItemService.deleteItemFromCart(user_id,productId);
        if(result) {
         return        ResponseEntity.ok("Product Removed from Cart successfully");
        }
        else{
             return ResponseEntity.badRequest().body("Product or User are not exists");
        }

    }
    @GetMapping
    public ResponseEntity<List<CartItem>> fetchAllItemsFromCart(@RequestHeader("X-User-ID") String user_id){
      List<CartItem> cartlist  = cartItemService.FetchItemsFromCart(Long.valueOf(user_id));
      return ResponseEntity.ok(cartlist);

    }

}
