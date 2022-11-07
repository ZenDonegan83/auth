package com.inkd.auth.model.dto.user;

import java.io.Serializable;

public class UserForgetPasswordRQ implements Serializable {

    private static final long serialVersionUID = 2337964587151311247L;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
