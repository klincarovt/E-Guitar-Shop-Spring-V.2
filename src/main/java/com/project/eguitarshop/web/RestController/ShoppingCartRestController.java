package com.project.eguitarshop.web.RestController;

import com.project.eguitarshop.model.CartItem;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.model.ShoppingCart;
import com.project.eguitarshop.model.dto.ChargeRequest;
import com.project.eguitarshop.service.AuthenticationService;
import com.project.eguitarshop.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartRestController {

    private final ShoppingCartService shoppingCartService;
    private final AuthenticationService authenticationService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService, AuthenticationService authenticationService) {
        this.shoppingCartService = shoppingCartService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ShoppingCart createShoppingCart(){
        return this.shoppingCartService.getActiveShoppingCart(this.authenticationService.getCurrentUserId());
    }

    @PatchMapping("/{guitarId}/guitars")
    public ShoppingCart addGuitarToShoppingCart(@PathVariable Long id){
        return this.shoppingCartService.addGuitarToShoppingCart(
                this.authenticationService.getCurrentUserId(),id
        );
    }

    @DeleteMapping("/{guitarId}/guitars")
    public ShoppingCart removeGuitarFromShoppingCart(@PathVariable Long id){
        return this.shoppingCartService.removeGuitarFromShoppingCart(this.authenticationService.getCurrentUserId()
        ,id);
    }

    @PatchMapping("/cancel")
    public ShoppingCart cancelActiveShoppingCart(){
        return this.shoppingCartService.cancelActiveShoppingCart(this.authenticationService.getCurrentUserId());
    }
    @PostMapping("/checkout")
    public ShoppingCart checkoutShoppingCart(){
        ChargeRequest chargeRequest=new ChargeRequest();
        chargeRequest.setAmount(0);
        StringBuilder sb=new StringBuilder();
        List<Guitar> items=this.shoppingCartService.getActiveShoppingCart(this.authenticationService.getCurrentUserId()).getGuitars();
        for (Guitar c:items) {
            chargeRequest.setAmount(chargeRequest.getAmount()+c.getPrice());
            sb.append("Item: "+c.getName()+"Price: "+c.getPrice()+"Samples: "+c.getSamples()+"\n");
        }
        chargeRequest.setDescription(sb.toString());
        chargeRequest.setCurrency("Euro");
        return this.shoppingCartService.checkoutShoppingCart(this.authenticationService.getCurrentUserId(),
              chargeRequest);

    }
}

