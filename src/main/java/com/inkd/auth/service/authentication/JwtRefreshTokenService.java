package com.inkd.auth.service.authentication;

import com.inkd.auth.model.domain.authentication.JwtRefreshToken;
import com.inkd.auth.model.domain.user.User;

public interface JwtRefreshTokenService {

    JwtRefreshToken createRefreshToken(Long userId);

    User generateAccessTokenFromRefreshToken(String refreshTokenId);
}
