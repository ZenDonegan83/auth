package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class CardUpdateRQ implements Serializable {

    private Long customerID;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }
}
