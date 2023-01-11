package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class StripeCustomerCreateRQ implements Serializable {

    private Long customerID;

    private String description;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
