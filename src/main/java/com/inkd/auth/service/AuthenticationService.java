package com.inkd.auth.service;

import com.inkd.auth.entity.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
