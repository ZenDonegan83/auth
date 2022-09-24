package com.inkd.auth.repository;

import com.inkd.auth.entity.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, String> {

}
