package com.inkd.auth.model.dto.stripe;

public class CardHolderDTO {

    private String cardHolderStripeID;

    private String email;

    private String type;

    private String status;

    private String name;

    public String getCardHolderStripeID() {
        return cardHolderStripeID;
    }

    public void setCardHolderStripeID(String cardHolderStripeID) {
        this.cardHolderStripeID = cardHolderStripeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
