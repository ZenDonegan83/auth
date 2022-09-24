package com.inkd.auth.service;

import com.inkd.auth.entity.JwtRefreshToken;
import com.inkd.auth.entity.User;

public interface JwtRefreshTokenService {
    JwtRefreshToken createRefreshToken(Long userId);

    User generateAccessTokenFromRefreshToken(String refreshTokenId);

}
