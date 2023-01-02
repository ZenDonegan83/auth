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
}
