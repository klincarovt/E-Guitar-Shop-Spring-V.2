package com.project.eguitarshop.web.controller;

import com.project.eguitarshop.model.CartItem;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.model.ShoppingCart;
import com.project.eguitarshop.model.dto.ChargeRequest;
import com.project.eguitarshop.service.AuthenticationService;
import com.project.eguitarshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentController {

   @Value("${STRIPE_P_KEY}")
    private String publicKey;

    private final ShoppingCartService shoppingCartService;
    private final AuthenticationService authenticationService;


    public PaymentController(ShoppingCartService shoppingCartService,
                             AuthenticationService authenticationService) {
        this.shoppingCartService = shoppingCartService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/charge")
    public String getCheckoutPage(Model model) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.findActiveShoppingCartByUsername(this.authenticationService.getCurrentUserId());
            model.addAttribute("shoppingCart", shoppingCart);
            model.addAttribute("currency", "eur");
            int price=0;
            for(Guitar item :shoppingCart.getGuitars()){
                price+=item.getPrice();
            }
            model.addAttribute("amount", (int) (price));
            model.addAttribute("stripePublicKey", this.publicKey);
            return "checkout";
        } catch (RuntimeException ex) {
            return "redirect:/guitars?error=" + ex.getLocalizedMessage();
        }
    }

    @PostMapping("/charge")
    public String checkout(ChargeRequest chargeRequest, Model model) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.checkoutShoppingCart(this.authenticationService.getCurrentUserId(), chargeRequest);
            return "redirect:/guitars?message=Successful Payment";
        } catch (RuntimeException ex) {
            return "redirect:/payments/charge?error=" + ex.getLocalizedMessage();
        }
    }
}
