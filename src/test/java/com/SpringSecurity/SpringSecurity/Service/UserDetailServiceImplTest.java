package com.SpringSecurity.SpringSecurity.Service;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.JournalEntryRepository.UserRepo;
import com.SpringSecurity.SpringSecurity.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailServiceImplTest {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @MockitoBean
    private UserRepo userRepo;

    @Test
    void findbyUserNameTest(){
        UserEntity user = new UserEntity();
        user.setUsername("Name");
        user.setPassword("Name");
        user.setRoles((List<String>) Collections.singleton("USER"));


        when(userRepo.findByUsername("Name")).thenReturn(Optional.of(user));

        assertNotNull(userDetailService);
        assertEquals("Name",user.getUsername());
        assertEquals("Name",user.getPassword());
    }
}
