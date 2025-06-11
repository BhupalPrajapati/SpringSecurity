package com.SpringSecurity.SpringSecurity.Controller;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers() {
        List<?> allUsers = userEntryService.getAll();
        if (allUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody UserEntity user){
        userEntryService.saveAdmin(user);
    }
}
