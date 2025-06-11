package com.SpringSecurity.SpringSecurity.Controller;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserEntryService userEntryService;

    @Autowired
    public UserController(UserEntryService userEntryService) {
        this.userEntryService = userEntryService;
    }

    @PutMapping
    private ResponseEntity<?> updateUser(@RequestBody UserEntity userEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userInDb = userEntryService.findByUserName(username);
        if (userInDb != null) {
            userInDb.setUsername(userEntity.getUsername());
            userInDb.setPassword(userEntity.getPassword());
            userEntryService.saveNewEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
