package com.inkd.auth.service.authentication;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.user.UserDTO;
import com.inkd.auth.model.dto.user.UserSignInRQ;

public interface AuthenticationService {

    UserDTO signInAndReturnJWT(UserSignInRQ signInRQ) throws AppsException;
}
