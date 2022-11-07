package com.inkd.auth.model.dto.authentication;

import java.io.Serializable;

public class RefreshTokenRQ implements Serializable {

    private static final long serialVersionUID = 2579853133750665482L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
