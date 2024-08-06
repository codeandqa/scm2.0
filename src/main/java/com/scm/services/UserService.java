package com.scm.services;

import java.util.*;

import com.scm.entity.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updatUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String emailId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    // Add additional methods based on your business logic.

}
