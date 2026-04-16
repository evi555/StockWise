package com.ecommerce.order.services;

import com.ecommerce.order.dto.CartRequest;
import com.ecommerce.order.models.CartItem;
//import com.ecommerce.order.models.Product;
//import com.ecommerce.order.models.User;
import com.ecommerce.order.repository.CartItemRepository;
//import com.ecommerce.order.repository.ProductRepository;
//import com.ecommerce.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {
    private final  CartItemRepository cartItemRepository;
    //private final ProductRepository productRepository;
   // private final UserRepository userRepository;

    public Boolean addToBasket(String userId, CartRequest cartRequest) {
//        Optional<Product> product = productRepository.findById(cartRequest.getProductId());
//        if (product.isEmpty()) {
//            return false;
//        }
//        Product exsisting_product = product.get();
//        if (exsisting_product.getStock_quantity() < cartRequest.getQuantity()) {
//            return false;
//        }
//        Optional<User> user = userRepository.findById(Long.valueOf(userId));
//        if (user.isEmpty()) {
//            return false;
//        }
//        User existing_user = user.get();
        CartItem cartItem = cartItemRepository.findByProductIdAndUserId(String.valueOf(cartRequest.getProductId()),userId);
        if (cartItem == null) {
            CartItem cartItem_new = new CartItem();
            cartItem_new.setQuantity(cartRequest.getQuantity());
            cartItem_new.setPrice(BigDecimal.valueOf(1000));
            cartItem_new.setUserId(Long.valueOf(userId));
            cartItem_new.setProductId(Long.valueOf(String.valueOf(cartRequest.getProductId())));
            cartItemRepository.save(cartItem_new);
        } else {

            cartItem.setQuantity(cartItem.getQuantity() + cartRequest.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public Boolean deleteItemFromCart(String userId, Long productId) {
       // Optional<User> user = userRepository.findById(Long.valueOf(userId));
      //  Optional<Product> product = productRepository.findById(productId);
       // if(user.isEmpty()){
       //     return false;
      //  }
      //  if(product.isEmpty()){
         //   return false;
      //  }
       // User existing_user = user.get();
       // Product existing_product = product.get();
        cartItemRepository.deleteByProductIdAndUserId(String.valueOf(productId),userId);
        return true;


    }

    public List<CartItem> FetchItemsFromCart(Long userId) {
        return cartItemRepository.findAllByUserId(String.valueOf(userId));

    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
    //private CartResponse mapToCartResponse(CartItem cartitem) {
      //  CartResponse cartResponse = new CartResponse();
        //cartResponse.setId(cartitem.getId());
        //cartResponse.setPrice(cartitem.getPrice());
        //cartResponse.setQuantity(cartitem.getQuantity());
        //cartResponse.setProduct(cartitem.getProduct());
        //return cartResponse;

    //}
}
