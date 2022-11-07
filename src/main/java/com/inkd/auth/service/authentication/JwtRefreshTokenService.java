package com.inkd.auth.service.authentication;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.authentication.JwtRefreshToken;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.user.UserDTO;

public interface JwtRefreshTokenService {

    JwtRefreshToken createRefreshToken(Long userId);

    UserDTO generateAccessTokenFromRefreshToken(String refreshTokenId) throws AppsException;
}
