package com.app.ecom.repository;

import com.app.ecom.models.CartItem;
import com.app.ecom.models.Product;
import com.app.ecom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByProductAndUser(Product product, User user);

    void deleteByProductAndUser(Product product, User user);

    List<CartItem> findAllByUserId(long user_id);

    void deleteByUser(User user);
}
