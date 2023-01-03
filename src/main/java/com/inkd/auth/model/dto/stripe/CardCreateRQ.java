package com.inkd.auth.model.dto.stripe;

import java.io.Serializable;

public class CardCreateRQ implements Serializable {

    public String stripeCardHolder;

    public String getStripeCardHolder() {
        return stripeCardHolder;
    }

    public void setStripeCardHolder(String stripeCardHolder) {
        this.stripeCardHolder = stripeCardHolder;
    }
}
