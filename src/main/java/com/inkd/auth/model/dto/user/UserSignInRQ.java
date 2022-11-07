package com.inkd.auth.model.dto.user;

import java.io.Serializable;

public class UserSignInRQ implements Serializable {

    private static final long serialVersionUID = 6273948879986759860L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
