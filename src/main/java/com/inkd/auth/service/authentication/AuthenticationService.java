package com.inkd.auth.service.authentication;

import com.inkd.auth.entity.user.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);
}
