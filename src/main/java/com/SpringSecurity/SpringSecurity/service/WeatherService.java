package com.SpringSecurity.SpringSecurity.service;

import com.SpringSecurity.SpringSecurity.APIIntegration.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_KEY = "97d5063b1678cb42eb20ffe7c0cd6351";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";


    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeather(String city) {
        String finaAPI = API.replace("CITY", city).replace("API_KEY", API_KEY);
        ResponseEntity<Weather> response = restTemplate.exchange(finaAPI, HttpMethod.GET, null, Weather.class);
        Weather body = response.getBody();
        return body;


    }


}
