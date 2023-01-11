package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class SubscriptionDTO implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
