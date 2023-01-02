package com.inkd.auth.service.stripe;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.model.dto.stripe.StripeCustomerCreateRQ;
import com.inkd.auth.model.dto.stripe.SubscriptionCreateRQ;
import com.inkd.auth.model.dto.stripe.CustomerCollectionDTO;
import com.inkd.auth.model.dto.stripe.SubscriptionDTO;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public interface StripeService {

    CustomerCollectionDTO getCustomers() throws StripeException, AppsException;

    SubscriptionDTO createSubscription(SubscriptionCreateRQ subscriptionCreateRQ) throws StripeException, AppsException;

    CustomerDTO createCustomer(StripeCustomerCreateRQ createRQ) throws StripeException, AppsException;

    CustomerDTO retrieveCustomer(String stripeID) throws StripeException, AppsException;;
}
