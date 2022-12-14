package com.inkd.auth.service.authentication;


import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.user.UserDTO;
import com.inkd.auth.model.dto.user.UserSignInRQ;
import com.inkd.auth.security.UserPrinciple;
import com.inkd.auth.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @Override
    public UserDTO signInAndReturnJWT(UserSignInRQ signInRQ) throws AppsException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRQ.getUsername(), signInRQ.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        User signInUser = userPrinciple.getUser();

        UserDTO signInUserDTO = new UserDTO(signInUser);

        signInUserDTO.setAccessToken(jwt);
        signInUserDTO.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInUser.getArtistID()).getTokenID());

        return signInUserDTO;
    }
}
