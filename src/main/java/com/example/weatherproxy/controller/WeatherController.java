package com.example.weatherproxy.controller;

import com.example.weatherproxy.service.OpenWeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final OpenWeatherService openWeatherService;

    public WeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> current(@RequestParam String city) {
        return ResponseEntity.ok(openWeatherService.getCurrentWeatherByCity(city));
    }
}
