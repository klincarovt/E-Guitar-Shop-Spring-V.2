package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.dto.ChargeRequest;
import com.project.eguitarshop.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImplementation implements PaymentService {

    @Value("")
    public String seretKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey=this.seretKey;
    }

    @Override
    public Charge pay(ChargeRequest chargeRequest) throws CardException, ApiException,
            AuthenticationException, InvalidRequestException, ApiConnectionException {

        Map<String, Object> chargeMap = new HashMap<>();
        chargeMap.put("amount", chargeRequest.getAmount());
        chargeMap.put("currency", chargeRequest.getCurrency());
        chargeMap.put("source", chargeRequest.getStripeToken());
        chargeMap.put("description", chargeRequest.getDescription());
        try {
            return Charge.create(chargeMap);
        } catch (StripeException e) {
            return null;
        }

    }
}
