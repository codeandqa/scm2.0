package com.scm.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundExection;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updatUser(User user) {

        User UserToUpdate = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundExection("User Not found"));

        UserToUpdate.setName(user.getName());
        UserToUpdate.setEmail(user.getEmail());
        UserToUpdate.setPassword(user.getPassword());
        UserToUpdate.setPhoneNumber(user.getPhoneNumber());
        UserToUpdate.setAbout(user.getAbout());
        UserToUpdate.setProfilePic(user.getProfilePic());
        UserToUpdate.setEmailVerified(user.isEmailVerified());
        UserToUpdate.setProvider(user.getProvider());
        UserToUpdate.setPhoneVerified(user.isPhoneVerified());
        UserToUpdate.setEnabled(user.isEnabled());
        UserToUpdate.setProviderUserId(user.getProviderUserId());

        User savedUser = userRepo.save(UserToUpdate);

        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User userToDelete = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExection("User Not found"));
        userRepo.delete(userToDelete);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId)
                .orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user2 = userRepo.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}