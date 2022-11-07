package com.inkd.auth.service.user;

import com.inkd.auth.constants.Role;
import com.inkd.auth.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User saveUser(User save);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();
}
