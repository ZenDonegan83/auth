package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class SubscriptionUpdateRQ implements Serializable {

    private String subscriptionID;

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }
}
