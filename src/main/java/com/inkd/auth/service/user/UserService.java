package com.inkd.auth.service.user;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserDTO saveUser(UserDTO userDTO) throws AppsException;

    Optional<User> findByUsername(String username);

    void changeRole(AppsConstants.Role newRole, String username);

    List<UserDTO> findAllUsers() throws AppsException;

    UserDTO updateUser(Long userID, UserDTO updateUserDTO) throws AppsException;

    UserDTO findByID(Long userID) throws AppsException;

    boolean existsById(Long userID) throws AppsException;

    User findUserByID(Long userID) throws AppsException;
}
