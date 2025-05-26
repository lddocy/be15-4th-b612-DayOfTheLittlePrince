package littleprince.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class OpenWeatherConfig {
    @Value("${OPEN_WEATHER_API_KEY}")
    private String apiKey;

    @Value("${OPEN_WEATHER_BASE_URL}")
    private String baseUrl;
}
