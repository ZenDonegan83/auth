package com.inkd.auth.service.stripe;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.model.dto.stripe.*;
import com.inkd.auth.service.customer.CustomerService;
import com.inkd.auth.utils.AppsUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.Subscription;
import com.stripe.model.issuing.Card;
import com.stripe.model.issuing.Cardholder;
import com.stripe.model.issuing.CardholderCollection;
import com.stripe.param.CustomerListParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secret.api.Key}")
    private String apiKey;

    @Autowired
    private CustomerService customerService;

    private void setAPIKey() {
        Stripe.apiKey = apiKey;
    }

    @Override
    public CustomerCollectionDTO getCustomers() throws StripeException, AppsException {
        this.setAPIKey();

        CustomerListParams params = CustomerListParams.builder().build();
        CustomerCollection customers = Customer.list(params);

        CustomerCollectionDTO customerCollectionDTO = AppsUtils.jsonToObjectMapper(customers.toJson(), CustomerCollectionDTO.class);

        return customerCollectionDTO;
    }

    @Override
    public SubscriptionDTO createSubscription(SubscriptionCreateRQ subscriptionCreateRQ) throws StripeException, AppsException {
        CustomerDTO customerDTO = this.customerService.findByID(subscriptionCreateRQ.getCustomerID());

        if (customerDTO.getStripeID() == null) {
            throw new AppsException("Customer Stripe ID is not found");
        } else {
            this.setAPIKey();

            List<Object> items = new ArrayList<>();

            // FIXME: 2023-01-03
            Map<String, Object> item1 = new HashMap<>();
            item1.put(
                    "price",
                    "price_1MBzNQLUDXVZUnKjmIqG7gVY"
            );
            items.add(item1);

            Map<String, Object> params = new HashMap<>();

            params.put("customer", customerDTO.getStripeID());
            params.put("items", items);

            Subscription subscription = Subscription.create(params);

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setId(subscription.getId());

            return subscriptionDTO;
        }
    }

    @Override
    public CustomerDTO createCustomer(StripeCustomerCreateRQ createRQ) throws StripeException, AppsException {
        CustomerDTO customerDTO = this.customerService.findByID(createRQ.getCustomerID());

        this.setAPIKey();

        Map<String, Object> params = new HashMap<>();
        params.put("description", createRQ.getDescription());
        params.put("email", customerDTO.getEmail());
        params.put("name", customerDTO.getLastName());

        Customer customer = Customer.create(params);

        customerDTO.setStripeID(customer.getId());
        customerDTO = this.customerService.updateStripeID(customerDTO);

        return customerDTO;
    }

    @Override
    public CustomerDTO retrieveCustomer(String stripeID) throws StripeException, AppsException {
        this.setAPIKey();

        if (stripeID == null) {
            throw new AppsException("Customer Stripe ID is not valid");
        } else {
            Customer stripeCustomer = Customer.retrieve(stripeID);

            if (stripeCustomer == null) {
                throw new AppsException("Customer not found in Stripe");
            } else {
                com.inkd.auth.model.domain.customer.Customer customer = this.customerService.findByStripeID(stripeCustomer.getId());
                return new CustomerDTO(customer);
            }
        }
    }

    @Override
    public CustomerDTO updateCustomer(StripeCustomerUpdateRQ updateRQ) throws StripeException, AppsException {
        Customer stripeCustomer = Customer.retrieve(updateRQ.getCustomerStripeID());

        if (stripeCustomer == null) {
            throw new AppsException("Customer not found in Stripe");
        } else {
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("order_id", "6735");
            Map<String, Object> params = new HashMap<>();
            params.put("metadata", metadata);

            Customer updatedCustomer = stripeCustomer.update(params);

            com.inkd.auth.model.domain.customer.Customer customer = this.customerService.findByStripeID(updatedCustomer.getId());

            return new CustomerDTO(customer);
        }
    }

    @Override
    public SubscriptionDTO updateSubscription(SubscriptionUpdateRQ updateRQ) throws StripeException, AppsException {
        Subscription subscription = Subscription.retrieve(updateRQ.getSubscriptionID());

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("order_id", "6735");
        Map<String, Object> params = new HashMap<>();
        params.put("metadata", metadata);

        Subscription updatedSubscription = subscription.update(params);

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setId(updatedSubscription.getId());

        return subscriptionDTO;
    }

    @Override
    public SubscriptionDTO cancelSubscription(SubscriptionCancelRQ cancelRQ) throws StripeException, AppsException {
        Subscription subscription = Subscription.retrieve(cancelRQ.getSubscriptionID());

        Subscription deletedSubscription = subscription.cancel();

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setId(deletedSubscription.getId());

        return subscriptionDTO;
    }

    @Override
    public CardDTO createCard(CardCreateRQ createRQ) throws StripeException, AppsException {

        this.setAPIKey();

        Map<String, Object> params = new HashMap<>();

        params.put("cardholder", createRQ.getStripeCardHolder());
        params.put("currency", "eur");
        params.put("type", "virtual");

        Card card = Card.create(params);

        CardDTO cardDTO = new CardDTO();

        cardDTO.setStripeCardID(card.getId());
        cardDTO.setCurrency(card.getCurrency());
        cardDTO.setType(card.getType());

        return cardDTO;
    }

    @Override
    public CardHolderDTO createCardHolder(CardHolderCreateRQ createRQ) throws StripeException, AppsException {
        this.setAPIKey();

        Map<String, Object> address = new HashMap<>();
        address.put("line1", createRQ.getAddress().getLine1());
        address.put("city", createRQ.getAddress().getCity());
        address.put("state", createRQ.getAddress().getState());
        address.put("country", createRQ.getAddress().getCountry());
        address.put("postal_code", createRQ.getAddress().getPostalCode());

        Map<String, Object> billing = new HashMap<>();
        billing.put("address", address);

        Map<String, Object> params = new HashMap<>();

        params.put("type", createRQ.getType());
        params.put("name", createRQ.getName());
        params.put("email", createRQ.getEmail());
        params.put("phone_number", createRQ.getPhoneNumber());
        params.put("billing", billing);

        Cardholder cardholder = Cardholder.create(params);

        CardHolderDTO cardHolderDTO = new CardHolderDTO();

        cardHolderDTO.setCardHolderStripeID(cardholder.getId());
        cardHolderDTO.setEmail(cardholder.getEmail());
        cardHolderDTO.setType(cardholder.getType());
        cardHolderDTO.setStatus(cardholder.getStatus());
        cardHolderDTO.setName(cardholder.getName());

        return cardHolderDTO;
    }

    @Override
    public CardHolderDTO retrieveCardHolder(String cardHolderID) throws StripeException, AppsException {
        this.setAPIKey();

        Cardholder cardholder = Cardholder.retrieve(cardHolderID);

        CardHolderDTO cardHolderDTO = new CardHolderDTO();

        cardHolderDTO.setCardHolderStripeID(cardholder.getId());
        cardHolderDTO.setName(cardholder.getName());
        cardHolderDTO.setType(cardholder.getType());
        cardHolderDTO.setStatus(cardholder.getStatus());
        cardHolderDTO.setEmail(cardholder.getEmail());

        return cardHolderDTO;
    }

    @Override
    public List<CardHolderDTO> getAllCardHolders() throws StripeException, AppsException {
        this.setAPIKey();

        List<CardHolderDTO> cardHolderDTOList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        CardholderCollection cardholders = Cardholder.list(params);

        List<Cardholder> cardholderList = cardholders.getData();

        for (Cardholder cardholder : cardholderList) {
            CardHolderDTO cardHolderDTO = new CardHolderDTO();

            cardHolderDTO.setCardHolderStripeID(cardholder.getId());
            cardHolderDTO.setName(cardholder.getName());
            cardHolderDTO.setType(cardholder.getType());
            cardHolderDTO.setStatus(cardholder.getStatus());
            cardHolderDTO.setEmail(cardholder.getEmail());

            cardHolderDTOList.add(cardHolderDTO);
        }

        return cardHolderDTOList;
    }
}
