package com.SpringSecurity.SpringSecurity.Controller;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")

public class PublicUser {

    @Autowired
    private UserEntryService userEntryService;


    @GetMapping("/health-check")
    public String healthCheck() {
        return "HealthCheck";
    }

    @PostMapping("/create-user")
    private void createUser(@RequestBody UserEntity userEntity) {
        userEntryService.saveNewEntry(userEntity);
    }
}
