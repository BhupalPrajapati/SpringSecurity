package com.SpringSecurity.SpringSecurity.service;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.JournalEntryRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepo.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserEntity user = optionalUser.get();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // should be BCrypt encoded
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }

}
