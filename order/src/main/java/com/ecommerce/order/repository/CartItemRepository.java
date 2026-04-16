package com.ecommerce.order.repository;

import com.ecommerce.order.models.CartItem;
//import com.ecommerce.order.models.Product;
//import com.ecommerce.order.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByProductIdAndUserId(String productId, String userId);

    void deleteByProductIdAndUserId(String productId, String userId);

    List<CartItem> findAllByUserId(String userId);

    void deleteByUserId(String userId);
}
