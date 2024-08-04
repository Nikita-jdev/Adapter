package org.example.entity;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Builder
public class MessageOut {
    private String txt;
    private String createdDt;
    private int currentTemp;

    public void createDateRFC(LocalDateTime localDateTimeNow) {
        ZonedDateTime utcDateTime = localDateTimeNow.atZone(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        this.createdDt = formatter.format(utcDateTime);
    }
}