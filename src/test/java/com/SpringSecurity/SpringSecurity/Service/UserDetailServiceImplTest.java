//package com.SpringSecurity.SpringSecurity.Service;
//
//import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
//import com.SpringSecurity.SpringSecurity.JournalEntryRepository.UserRepo;
//import com.SpringSecurity.SpringSecurity.service.UserDetailServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//
//public class UserDetailServiceImplTest {
//
//    @InjectMocks
//    private UserDetailServiceImpl userDetailService;
//
//    @Mock
//    private UserRepo userRepo;
//
//    @BeforeEach
//    void testSetup(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    void findbyUserNameTest() {
//        UserEntity user = new UserEntity();
//        user.setUsername("Name");
//        user.setPassword("Name");
//        user.setRoles(Collections.singletonList("USER"));
//
//
//        when(userRepo.findByUsername("Name")).thenReturn(Optional.of(user));
//
//        assertNotNull(userDetailService);
//        assertEquals("Name", user.getUsername());
//        assertEquals("Name", user.getPassword());
//    }
//}
