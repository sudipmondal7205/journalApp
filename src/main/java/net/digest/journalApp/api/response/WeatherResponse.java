package net.digest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Setter
    @Getter
    public static class Condition {

        private String text;

        private String icon;

        private int code;
    }

    @Setter
    @Getter
    public static class Current {

        @JsonProperty("temp_c")
        private double tempC;

        private Condition condition;

        @JsonProperty("pressure_in")
        private double pressureIn;

        private int humidity;

        private int cloud;
    }
}
