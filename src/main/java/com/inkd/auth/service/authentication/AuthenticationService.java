package com.inkd.auth.service.authentication;

import com.inkd.auth.model.domain.user.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);
}
