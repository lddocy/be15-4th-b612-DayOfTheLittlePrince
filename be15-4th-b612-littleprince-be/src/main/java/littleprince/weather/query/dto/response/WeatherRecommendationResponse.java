package littleprince.weather.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherRecommendationResponse {
    private String weatherMain;
    private String description;
    private String recommendation;
}
