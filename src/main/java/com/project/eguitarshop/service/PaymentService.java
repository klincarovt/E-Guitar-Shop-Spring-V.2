package com.project.eguitarshop.service;

import com.project.eguitarshop.model.dto.ChargeRequest;
import com.stripe.exception.*;
import com.stripe.model.Charge;

public interface PaymentService {
    Charge pay(ChargeRequest chargeRequest) throws CardException, ApiException, AuthenticationException,
            InvalidRequestException, ApiConnectionException;
}
