package com.inkd.auth.service.stripe;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.model.dto.stripe.*;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StripeService {

    CustomerCollectionDTO getCustomers() throws StripeException, AppsException;

    SubscriptionDTO createSubscription(SubscriptionCreateRQ subscriptionCreateRQ) throws StripeException, AppsException;

    CustomerDTO createCustomer(StripeCustomerCreateRQ createRQ) throws StripeException, AppsException;

    CustomerDTO retrieveCustomer(String stripeID) throws StripeException, AppsException;

    CustomerDTO updateCustomer(StripeCustomerUpdateRQ updateRQ) throws StripeException, AppsException;

    SubscriptionDTO updateSubscription(SubscriptionUpdateRQ updateRQ) throws StripeException, AppsException;

    SubscriptionDTO cancelSubscription(SubscriptionCancelRQ cancelRQ) throws StripeException, AppsException;

    CardHolderDTO createCardHolder(CardHolderCreateRQ createRQ) throws StripeException, AppsException;

    CardHolderDTO retrieveCardHolder(String cardHolderID) throws StripeException, AppsException;

    List<CardHolderDTO> getAllCardHolders() throws StripeException, AppsException;

    CardDTO createCard(CardCreateRQ createRQ) throws StripeException, AppsException;

    CardDTO retrieveCard(String cardID) throws StripeException, AppsException;

    List<CardDTO> getAllCards() throws StripeException, AppsException;
}
