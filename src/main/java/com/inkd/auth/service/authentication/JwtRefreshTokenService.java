package com.inkd.auth.service.authentication;

import com.inkd.auth.entity.authentication.JwtRefreshToken;
import com.inkd.auth.entity.user.User;

public interface JwtRefreshTokenService {

    JwtRefreshToken createRefreshToken(Long userId);

    User generateAccessTokenFromRefreshToken(String refreshTokenId);
}
