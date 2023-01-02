package com.inkd.auth.service.stripe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.stripe.CustomerCollectionDTO;
import com.inkd.auth.utils.AppsUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerListParams;
import org.springframework.stereotype.Service;

@Service
public interface StripeService {

    CustomerCollectionDTO getCustomers() throws StripeException, AppsException;
}
