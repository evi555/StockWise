package com.ecommerce.product.services;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.models.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductResponse ProductRequestCreate(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product,productRequest);
       Product saveProduct = productRepository.save(product);
        return mapToProductResponse(saveProduct);

    }

    private ProductResponse mapToProductResponse(Product saveProduct) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCategory(saveProduct.getCategory());
        productResponse.setName(saveProduct.getName());
        productResponse.setPrice(saveProduct.getPrice());
        productResponse.setDescription(saveProduct.getDescription());
        productResponse.setId(saveProduct.getId());
        productResponse.setImageUrl(saveProduct.getImageUrl());
        productResponse.setStock_quantity(saveProduct.getStock_quantity());
        productResponse.setActive(saveProduct.getActive());
        return productResponse;

    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStock_quantity(productRequest.getStock_quantity());
    }

    @Transactional
    public ProductResponse ProductRequestUpdate(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found with id:" + id));
        updateProductFromRequest(product,productRequest);
         //productRepository.save(product);
         return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
       return productRepository.findByActiveTrue().stream().map(this::mapToProductResponse).collect(Collectors.toList());

    }

    public Boolean deleteProduct(long id) {
      return productRepository.findById(id).map(product -> {
            product.setActive(false);
            productRepository.save(product);
            return true;
        }).orElse(false);


    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword).stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }
}
