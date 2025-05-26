package littleprince.weather.query.service;

import littleprince.config.OpenWeatherConfig;
import littleprince.weather.query.dto.WeatherDTO;
import littleprince.weather.query.dto.response.WeatherResponse;
import littleprince.weather.query.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WeatherQueryService {
    private final OpenWeatherConfig config;
    private final WeatherMapper weatherMapper;

    public WeatherResponse getWeather(double lat, double lon) {
        WebClient client = WebClient.builder()
                .baseUrl(config.getBaseUrl())
                .build();

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("appid", config.getApiKey())
                        .queryParam("units", "metric")
                        .queryParam("lang", "kr")
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }

    public String getRecommendation(String weatherMain) {
        WeatherDTO dto = weatherMapper.findByWeatherMain(weatherMain);
        return dto != null ? dto.getRecommendation() : "오늘의 날씨에 맞게 자유롭게 시간을 보내보세요!";
    }

}