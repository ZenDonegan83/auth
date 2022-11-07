package com.inkd.auth.repository.authentication;

import com.inkd.auth.entity.authentication.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, String> {

}
