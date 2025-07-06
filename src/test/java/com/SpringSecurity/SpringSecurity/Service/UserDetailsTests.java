//package com.SpringSecurity.SpringSecurity.Service;
//
//import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
//import com.SpringSecurity.SpringSecurity.JournalEntryRepository.UserRepo;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserDetailsTests {
//
//
//    @Autowired
//    private UserRepo userRepo ;
//
//    @Autowired
//    private UserEntity userEntity;
//    @Test
//    public void addtwo(){
//        Optional<UserEntity> user = userRepo.findByUsername("bhupal");
//        assertNotNull(user, "User 'bhupal' should exist in the database");
//
//    }
//
//
//
//
//}
