package com.inkd.auth.service.user;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.user.UserDTO;
import com.inkd.auth.repository.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws AppsException {
        User user = new User();

        //Validate User DTO
        this.validateUserDTO(userDTO, true);

        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setProfilePic(userDTO.getProfilePic());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(AppsConstants.Role.USER);
        user.setCreateDate(new Date());

        user = this.userRepository.saveAndFlush(user);

        return new UserDTO(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void changeRole(AppsConstants.Role newRole, String username) {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public List<UserDTO> findAllUsers() throws AppsException {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            userDTOList.add(new UserDTO(user));
        }

        return userDTOList;
    }

    @Override
    public UserDTO updateUser(Long userID, UserDTO updateUserDTO) throws AppsException {
        if (userID == null) {
            throw new AppsException("User ID is not valid");
        }

        if (!userRepository.existsById(userID)) {
            throw new AppsException("User is not found");
        } else {
            User user = userRepository.getById(userID);

            //Validate User DTO
            this.validateUserDTO(updateUserDTO, false);

            if (!user.getUsername().equalsIgnoreCase(updateUserDTO.getUsername())
                    && this.userRepository.findByUsernameIgnoreCase(updateUserDTO.getUsername()) != null) {
                throw new AppsException("Username is already exist");
            }
            if (!user.getEmail().equalsIgnoreCase(updateUserDTO.getEmail())
                    && this.userRepository.findByEmailIgnoreCase(updateUserDTO.getEmail()) != null) {
                throw new AppsException("Email is already exist");
            }

            user.setUsername(updateUserDTO.getUsername());
            user.setFirstName(updateUserDTO.getFirstName());
            user.setLastName(updateUserDTO.getLastName());
            user.setEmail(updateUserDTO.getEmail());
            user.setProfilePic(updateUserDTO.getProfilePic());
            user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));

            user = this.userRepository.saveAndFlush(user);

            return new UserDTO(user);
        }
    }

    private void validateUserDTO(UserDTO userDTO, boolean isNew) throws AppsException {
        if (StringUtils.isEmpty(userDTO.getUsername())) {
            throw new AppsException("User name is not valid");
        }

        if (StringUtils.isEmpty(userDTO.getEmail())) {
            throw new AppsException("Email is not valid");
        }

        if (isNew) {
            if (this.userRepository.findByUsernameIgnoreCase(userDTO.getUsername()) != null) {
                throw new AppsException("User name already exist");
            }

            if (this.userRepository.findByEmailIgnoreCase(userDTO.getEmail()) != null) {
                throw new AppsException("Email already exist");
            }
        }

        if (StringUtils.isEmpty(userDTO.getFirstName())) {
            throw new AppsException("First name can't be empty or null");
        }

        if (StringUtils.isEmpty(userDTO.getLastName())) {
            throw new AppsException("Last name can't be empty or null");
        }

        if (StringUtils.isEmpty(userDTO.getPassword())) {
            throw new AppsException("Password is not valid");
        }
    }
}
