package com.inkd.auth.model.dto.customer;

import com.inkd.auth.model.domain.customer.Customer;

import java.io.Serializable;
import java.util.Date;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 835777959887160026L;

    private Long customerID;

    private String stripeID;

    private String firstName;

    private String lastName;

    private String telNumber;

    private String email;

    private Date createDate;

    private byte[] profilePic;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.customerID = customer.getCustomerID();
        this.stripeID = customer.getStripeID();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.telNumber = customer.getTelNumber();
        this.email = customer.getEmail();
        this.createDate = customer.getCreateDate();
        this.profilePic = customer.getProfilePic();
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getStripeID() {
        return stripeID;
    }

    public void setStripeID(String stripeID) {
        this.stripeID = stripeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
}
