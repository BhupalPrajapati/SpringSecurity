package com.SpringSecurity.SpringSecurity.Controller;

import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.service.UserDetailServiceImpl;
import com.SpringSecurity.SpringSecurity.service.UserEntryService;
import com.SpringSecurity.SpringSecurity.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicUser {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "HealthCheck";
    }

    @PostMapping("/signup")
    private void signup(@RequestBody UserEntity userEntity) {
        userEntryService.saveNewEntry(userEntity);
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(userEntity.getUsername());
            String jwt = jwtUtil.generatToken(userDetails.getUsername());
            return new ResponseEntity<>("JWT KEY: "+jwt, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Exception occurred while createAuthentication", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
