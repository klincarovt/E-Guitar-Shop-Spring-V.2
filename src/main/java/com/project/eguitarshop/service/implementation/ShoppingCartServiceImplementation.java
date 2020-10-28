package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.model.ShoppingCart;
import com.project.eguitarshop.model.User;
import com.project.eguitarshop.model.dto.ChargeRequest;
import com.project.eguitarshop.model.enumeration.CartStatus;
import com.project.eguitarshop.model.exceptions.*;
import com.project.eguitarshop.repository.ShoppingCartRepository;
import com.project.eguitarshop.service.*;
import com.stripe.exception.ApiException;
import com.stripe.exception.CardException;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    private final UserService userService;
    private final GuitarService guitarService;

    private final PaymentService paymentService;


    public ShoppingCartServiceImplementation(ShoppingCartRepository shoppingCartRepository,
                                             UserService userService,
                                             GuitarService guitarService,
                                             PaymentService paymentService
                                            ) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.guitarService = guitarService;
        this.paymentService = paymentService;

    }

    @Override
    public ShoppingCart findActiveShoppingCartByUsername(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndStatus(userId, CartStatus.CREATED).orElseThrow(
                () -> new ShoppingCartNotActive()
        );
    }

    @Override
    public List<ShoppingCart> findAllByUsername(String userId) {
        return this.shoppingCartRepository.findAllByUserUsername(userId);
    }

    @Override
    public ShoppingCart createNewShoppingCart(String userId) {
        User user = this.userService.findByName(userId);
        if(this.shoppingCartRepository.existsByUserUsernameAndStatus(user.getUsername(),CartStatus.CREATED)){
            throw new ShoppingCartExists();
        }
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart addGuitarToShoppingCart(String userId, Long productId) {

        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Guitar guitar =this.guitarService.findById(productId);


        for (Guitar c : shoppingCart.getGuitars()) {
            if (c.getId().equals(productId)) {
                throw new GuitarAlreadyInShoppingCartException(guitar.getName());
            }
        }
        shoppingCart.getGuitars().add(guitar);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeGuitarFromShoppingCart(String userId, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);

        shoppingCart.setGuitars(
                shoppingCart.getGuitars().stream().filter(
                        cartItem -> !cartItem.getId().equals(productId)
                ).collect(Collectors.toList())
        );

        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndStatus(
                userId,CartStatus.CREATED
        ).orElseGet(
                () -> {
                    ShoppingCart shoppingCart= new ShoppingCart();
                    User user =this.userService.findByName(userId);
                    shoppingCart.setUser(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                }
        );
    }

    @Override
    public ShoppingCart cancelActiveShoppingCart(String userId) {
        ShoppingCart shoppingCart=this.shoppingCartRepository.findByUserUsernameAndStatus
                (userId,CartStatus.CREATED).orElseThrow(
                        () -> new ShoppingCartNotActive()
                );

        shoppingCart.setStatus(CartStatus.CANCELED);

        return this.shoppingCartRepository.save(shoppingCart);
    }

    //TODO
    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart=this.shoppingCartRepository.findByUserUsernameAndStatus(userId,CartStatus.CREATED).orElseThrow(
                () -> new ShoppingCartNotActive()
        );

        List<Guitar> items=shoppingCart.getGuitars();
        float price=0;

        for(Guitar g : items){
            if(g.getSamples()<=0){
                throw new OutOfStockException();
            }
            g.setSamples(g.getSamples()-1);
            price+=g.getPrice();
        }
        Charge charge=null;
        try{
            charge=this.paymentService.pay(chargeRequest);
        }catch (CardException | ApiException | AuthenticationException | ApiConnectionException | InvalidRequestException e){
            throw new TransactionFailedException(userId, e.getMessage());
        }

        shoppingCart.setGuitars(items);
        shoppingCart.setStatus(CartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
