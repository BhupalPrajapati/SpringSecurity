package com.SpringSecurity.SpringSecurity.APIIntegration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Weather {
    private Current current;

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("wind_dir")
        private String windDescription;

        private int feelslike;
    }
}
