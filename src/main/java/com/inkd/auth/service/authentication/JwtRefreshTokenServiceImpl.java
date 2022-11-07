package com.inkd.auth.service.authentication;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.authentication.JwtRefreshToken;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.user.UserDTO;
import com.inkd.auth.repository.authentication.JwtRefreshTokenRepository;
import com.inkd.auth.repository.user.UserRepository;
import com.inkd.auth.security.UserPrinciple;
import com.inkd.auth.security.jwt.JwtProvider;
import com.inkd.auth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {

    @Value("${app.jwt.refresh-expiration-in-ms}")
    private Long REFRESH_EXPIRATION_IN_MS;

    @Autowired
    private JwtRefreshTokenRepository jwtRefreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public JwtRefreshToken createRefreshToken(Long userId) {
        JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();

        jwtRefreshToken.setTokenID(UUID.randomUUID().toString());
        jwtRefreshToken.setUserID(userId);
        jwtRefreshToken.setCreateDate(LocalDateTime.now());
        jwtRefreshToken.setExpirationDate(LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));

        return jwtRefreshTokenRepository.save(jwtRefreshToken);
    }

    @Override
    public UserDTO generateAccessTokenFromRefreshToken(String refreshTokenId) throws AppsException {
        JwtRefreshToken jwtRefreshToken = jwtRefreshTokenRepository.findById(refreshTokenId).orElseThrow();

        if (jwtRefreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("JWT refresh token not valid");
        }

        User user = userRepository.findById(jwtRefreshToken.getUserID()).orElseThrow();

        UserPrinciple userPrinciple = UserPrinciple.builder()
                .id(user.getArtistID())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Set.of(SecurityUtils.convertToAuthority(user.getRole().name())))
                .build();

        String accessToken = jwtProvider.generateToken(userPrinciple);

        UserDTO userDTO = new UserDTO(user);

        userDTO.setAccessToken(accessToken);
        userDTO.setRefreshToken(refreshTokenId);

        return userDTO;
    }
}
