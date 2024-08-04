package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ServiceBClient;
import org.example.client.WeatherServiceClient;
import org.example.entity.MessageIn;
import org.example.entity.MessageOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherServiceClient weatherServiceClient;
    private final ServiceBClient serviceBClient;

    @Value("${gismeteo.txt}")
    private String txt;

    public ResponseEntity<String> addTemp(MessageIn messageIn) {
        if (messageIn.getLng() != MessageIn.Language.ru) {
            log.info("Language not ru");
        }
        MessageOut messageOut = MessageOut.builder()
                .txt(txt)
                .currentTemp(getCurrentTemperature(messageIn))
                .build();
        messageOut.createDateRFC(LocalDateTime.now());

        serviceBClient.sendCurrentTemp(messageOut);
        return ResponseEntity.status(200).body("Temperature sent");
    }

    private int getCurrentTemperature(MessageIn messageIn) {
        return weatherServiceClient.getCurrentTemperature(messageIn.getCoordinates())
                .orElseThrow(() -> new RuntimeException("No response from weather service"));
    }
}
