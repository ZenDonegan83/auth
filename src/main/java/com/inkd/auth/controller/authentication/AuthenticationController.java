package com.inkd.auth.controller.authentication;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.user.UserDTO;
import com.inkd.auth.model.dto.user.UserForgetPasswordRQ;
import com.inkd.auth.model.dto.user.UserSignInRQ;
import com.inkd.auth.service.authentication.AuthenticationService;
import com.inkd.auth.service.authentication.JwtRefreshTokenService;
import com.inkd.auth.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseDTO<UserDTO>> signUp(@RequestBody UserDTO userDTO) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            UserDTO savedUserDTO = userService.saveUser(userDTO);

            response.setResult(savedUserDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("sign-in")
    public ResponseEntity<ResponseDTO<UserDTO>> signIn(@RequestBody UserSignInRQ signInRQ) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            UserDTO signedUserDTO = authenticationService.signInAndReturnJWT(signInRQ);

            response.setResult(signedUserDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("deleted")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        return null;
    }

    @PostMapping("refresh-token")
    public ResponseEntity<ResponseDTO<UserDTO>> refreshToken(@RequestParam String token) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            UserDTO signedUserDTO = jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token);

            response.setResult(signedUserDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/allUsers", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getAllUsers() {
        ResponseDTO<List<UserDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<UserDTO> allProducts = this.userService.findAllUsers();

            response.setResult(allProducts);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updateUser/{userID}")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@PathVariable Long userID, @RequestBody UserDTO updateUserDTO) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            UserDTO userDTO = this.userService.updateUser(userID, updateUserDTO);

            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("forgetPassword")
    public ResponseEntity<ResponseDTO<String>> forgetPassword(@RequestBody UserForgetPasswordRQ forgetPasswordRQ) {
        ResponseDTO<String> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            userService.forgetPassword(forgetPasswordRQ);

            response.setResult("SUCCESS");
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
