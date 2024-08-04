package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class WeatherResponse {
    private ResponseObject responseObject;

    @JsonProperty("response")
    public ResponseObject getResponseObject() {
        return responseObject;
    }

    @Setter
    public static class ResponseObject {
        private Temperature temperature;

        @JsonProperty("temperature")
        public Temperature getTemperature() {
            return temperature;
        }
    }

    @Setter
    public static class Temperature {
        private int air;

        @JsonProperty("air")
        public int getAir() {
            return air;
        }
    }
}
