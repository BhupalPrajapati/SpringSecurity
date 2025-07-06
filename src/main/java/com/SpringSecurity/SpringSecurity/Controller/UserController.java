package com.SpringSecurity.SpringSecurity.Controller;

import com.SpringSecurity.SpringSecurity.APIIntegration.Weather;
import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.service.UserEntryService;
import com.SpringSecurity.SpringSecurity.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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


    //---------------------------------------------------------------------------------------------------------------------------------


    // Weather API integration


    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Weather weather = weatherService.getWeather("Kathmandu");

        String msg = ", Weather data is unavailable.";

        if (weather != null && weather.getCurrent() != null) {
            msg = ", Weather feels like " + weather.getCurrent().getFeelslike() + " degree";
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + msg, HttpStatus.OK);
    }


}
