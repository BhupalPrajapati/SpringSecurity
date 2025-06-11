package com.SpringSecurity.SpringSecurity.service;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.JournalEntryRepository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Save a user entity
    public void saveNewEntry(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("User"));
        userRepo.save(userEntity);
    }

    // save the admin

    public void saveAdmin(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("User", "ADMIN"));
        userRepo.save(userEntity);
    }

    public void saveUser(UserEntity userEntity) {
        userRepo.save(userEntity);
    }

    // Retrieve all users
    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    // Find a user by ID
    public Optional<UserEntity> findByID(ObjectId id) {
        return userRepo.findById(id);
    }

    // Delete a user by ID
    public void deleteById(ObjectId id) {
        userRepo.deleteById(id);
    }

    // Find a user by username
    public UserEntity findByUserName(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

}
