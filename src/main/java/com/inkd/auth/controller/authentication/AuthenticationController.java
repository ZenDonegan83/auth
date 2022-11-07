package com.inkd.auth.controller.authentication;


import com.inkd.auth.entity.user.User;
import com.inkd.auth.service.authentication.AuthenticationService;
import com.inkd.auth.service.authentication.JwtRefreshTokenService;
import com.inkd.auth.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @DeleteMapping("deleted")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        return null;
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
    }
}
