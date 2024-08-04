package org.example.entity;

import lombok.Data;

@Data
public class MessageIn {
    private String msg;
    private Language lng;
    private Coordinates coordinates;

    public enum Language {
        ru, en, es
    }

    @Data
    public static class Coordinates {
        private String latitude;
        private String longitude;
    }
}