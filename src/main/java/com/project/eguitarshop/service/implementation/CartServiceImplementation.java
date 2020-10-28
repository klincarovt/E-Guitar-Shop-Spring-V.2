package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.service.CartService;

public class CartServiceImplementation implements CartService {

  /*  private final CartItemRepository cartItemRepository;

    public CartServiceImplementation(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;

    }

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public CartItem findById(Long id) {
        return this.cartItemRepository.findById(id).orElseThrow(
                ()->new CartItemNotFound(id)
        );
    }

    @Override
    public CartItem save(CartItem item) {
        return this.cartItemRepository.save(item);
    }

    @Override
    public CartItem update(Long id,Guitar guitar) {
        CartItem cartItem = this.findById(id);

        cartItem.setGuitar(.getGuitar());
        cartItem.setShoppingCart(item.getShoppingCart());
        cartItem.setQuantity(item.getQuantity());

        return this.cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id) {
        this.cartItemRepository.deleteById(id);
    }*/
}


