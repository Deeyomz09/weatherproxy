package com.example.weatherproxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class OpenWeatherService {

    private final RestTemplate restTemplate;

    @Value("${openweather.base-url}")
    private String baseUrl;

    @Value("${openweather.api-key}")
    private String apiKey;

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getCurrentWeatherByCity(String city) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("OPENWEATHER_API_KEY is missing");
        }

        String url = UriComponentsBuilder
                .fromUriString(baseUrl + "/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .build()
                .toUriString();

        return restTemplate.getForObject(url, Map.class);
    }
}
