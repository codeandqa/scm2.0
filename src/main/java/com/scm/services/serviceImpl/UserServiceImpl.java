package com.scm.services.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entity.User;
import com.scm.helper.ResourceNotFoundExection;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updatUser(User user) {

        User UserToUpdate = userRepo.findById(user.getUserid())
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExistByEmail'");
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

}
