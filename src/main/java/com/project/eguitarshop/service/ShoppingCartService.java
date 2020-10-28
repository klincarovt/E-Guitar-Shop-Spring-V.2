package com.project.eguitarshop.service;

import com.project.eguitarshop.model.ShoppingCart;
import com.project.eguitarshop.model.dto.ChargeRequest;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart findActiveShoppingCartByUsername(String userId);
    List<ShoppingCart> findAllByUsername(String userId);
    ShoppingCart createNewShoppingCart(String userId);
    ShoppingCart addGuitarToShoppingCart(String userId, Long productId);
    ShoppingCart removeGuitarFromShoppingCart(String userId, Long productId);
    ShoppingCart getActiveShoppingCart(String userId);
    ShoppingCart cancelActiveShoppingCart(String userId);
    ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest);

}
