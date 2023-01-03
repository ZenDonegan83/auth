package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class CardDTO implements Serializable {

    private String stripeCardID;

    private String currency;

    private String type;

    public String getStripeCardID() {
        return stripeCardID;
    }

    public void setStripeCardID(String stripeCardID) {
        this.stripeCardID = stripeCardID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
