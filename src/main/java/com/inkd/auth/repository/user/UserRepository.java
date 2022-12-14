package com.inkd.auth.repository.user;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.model.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User set role = :role where username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") AppsConstants.Role role);

    User findByUsernameIgnoreCase(String userName);

    User findByEmailIgnoreCase(String email);
}
