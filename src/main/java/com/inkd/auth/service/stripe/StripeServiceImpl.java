package com.inkd.auth.service.stripe;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.stripe.CustomerCollectionDTO;
import com.inkd.auth.utils.AppsUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerListParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secret.api.Key}")
    private String apiKey;

    private void setAPIKey() {
        Stripe.apiKey = apiKey;
    }

    @Override
    public CustomerCollectionDTO getCustomers() throws StripeException, AppsException {
        this.setAPIKey();

        CustomerListParams params = CustomerListParams.builder().build();
        CustomerCollection customers = Customer.list(params);

        CustomerCollectionDTO customerCollectionDTO;

        customerCollectionDTO = AppsUtils.jsonToObjectMapper(customers.toJson(), CustomerCollectionDTO.class);

        return customerCollectionDTO;
    }
}
