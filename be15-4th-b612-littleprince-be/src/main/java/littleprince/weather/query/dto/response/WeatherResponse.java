package littleprince.weather.query.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private Current current;

    @Data
    public static class Current {
        private double temp;
        private List<Weather> weather;
    }

    @Data
    public static class Weather {
        private String main;
        private String description;
    }
}
