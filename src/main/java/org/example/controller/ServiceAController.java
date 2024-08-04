package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.MessageIn;
import org.example.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AdapterController")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.serviceA_version}")
public class ServiceAController {

    private final WeatherService weatherService;

    @Operation(
            summary = "Добавляем погоду",
            description = "Сюда отправляем координаты и получаем погоду"
    )
    @PostMapping()
    public ResponseEntity<String> addTemp(@RequestBody MessageIn messageIn) {
        if (messageIn.getMsg() == null || messageIn.getMsg().isEmpty()) {
            return ResponseEntity.status(400).body("Msg is null");
        }
        return weatherService.addTemp(messageIn);
    }
}

