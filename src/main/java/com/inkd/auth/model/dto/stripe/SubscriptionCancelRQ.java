package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class SubscriptionCancelRQ implements Serializable {

    private String subscriptionID;

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }
}
