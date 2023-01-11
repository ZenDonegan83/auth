package com.inkd.auth.model.dto.stripe;

public class CardHolderCreateRQ {

    private CardHolderAddressDTO address;

    private String type;

    private String name;

    private String email;

    private String phoneNumber;

    public CardHolderAddressDTO getAddress() {
        return address;
    }

    public void setAddress(CardHolderAddressDTO address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
