package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class StripeCustomerUpdateRQ implements Serializable {

    private String customerStripeID;

    public String getCustomerStripeID() {
        return customerStripeID;
    }

    public void setCustomerStripeID(String customerStripeID) {
        this.customerStripeID = customerStripeID;
    }
}
