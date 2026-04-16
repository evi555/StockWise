package com.ecommerce.product.controllers;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
       return new ResponseEntity<ProductResponse>(productService.ProductRequestCreate(productRequest),HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());

    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword){
        return ResponseEntity.ok(productService. searchProduct(keyword));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProduct(@PathVariable long id){
       Boolean deleted = productService.deleteProduct(id);
        return   deleted ? ResponseEntity.noContent().build(): ResponseEntity.notFound().build();


    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable Long id){
        return new ResponseEntity<ProductResponse>(productService.ProductRequestUpdate(productRequest,id),HttpStatus.OK);

    }
}
