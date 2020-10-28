package com.project.eguitarshop.web.controller;

import com.project.eguitarshop.model.CartItem;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.model.ShoppingCart;
import com.project.eguitarshop.service.AuthenticationService;
import com.project.eguitarshop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthenticationService authenticationService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  AuthenticationService authenticationService) {
        this.shoppingCartService = shoppingCartService;
        this.authenticationService = authenticationService;
    }

   @GetMapping
    public String getShoppingCartPage(Model model){
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(this.authenticationService.getCurrentUserId());
        List<Guitar> items=shoppingCart.getGuitars();
        model.addAttribute("items",items);
        return "shopping-cart";
    }


    @PostMapping("/{id}/add-guitar")
    public String addGuitarToShoppingCart(@PathVariable Long id) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addGuitarToShoppingCart(
                    this.authenticationService.getCurrentUserId(), id);
        } catch (RuntimeException ex) {
            return "redirect:/shopping-cart?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/shopping-cart";
    }


    @PostMapping("/{id}/remove-guitar")
    public String removeGuitarToShoppingCart(@PathVariable Long id) {
        ShoppingCart shoppingCart = this.shoppingCartService.removeGuitarFromShoppingCart(this.authenticationService.getCurrentUserId(), id);
        return "redirect:/shopping-cart";
    }
}
