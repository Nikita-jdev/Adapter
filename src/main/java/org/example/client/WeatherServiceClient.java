package org.example.client;

import lombok.RequiredArgsConstructor;
import org.example.entity.MessageIn;
import org.example.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceClient {

    private final WebClient webClient;

    @Value("${gismeteo.head_name}")
    private String headName;

    @Value("${gismeteo.token}")
    private String apiToken;

    public WeatherServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("${gismeteo.host}").build();
    }

    public Optional<Integer> getCurrentTemperature(MessageIn.Coordinates coordinates) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", coordinates.getLatitude())
                        .queryParam("longitude", coordinates.getLongitude())
                        .build())
                .header(headName, apiToken)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .map(response -> response.getResponseObject().getTemperature().getAir()).blockOptional();
    }
}
