package littleprince.weather.query.dto;

import lombok.Data;

@Data
public class WeatherDTO {

    private String weatherMain;

    private String recommendation;
}
