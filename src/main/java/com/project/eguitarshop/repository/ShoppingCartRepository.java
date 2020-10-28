package com.project.eguitarshop.repository;

import com.project.eguitarshop.model.ShoppingCart;
import com.project.eguitarshop.model.enumeration.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart,Long>{
    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, CartStatus status);
    boolean existsByUserUsernameAndStatus(String username, CartStatus status);
    List<ShoppingCart> findAllByUserUsername(String username);
}
