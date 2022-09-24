package com.inkd.auth.service;

import com.inkd.auth.entity.Role;
import com.inkd.auth.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User save);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();
}
