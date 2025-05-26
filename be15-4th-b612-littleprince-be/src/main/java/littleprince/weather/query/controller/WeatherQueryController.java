package littleprince.weather.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.weather.query.dto.response.WeatherRecommendationResponse;
import littleprince.weather.query.dto.response.WeatherResponse;
import littleprince.weather.query.service.WeatherQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="weather")
@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherQueryController {

    private final WeatherQueryService weatherQueryService;

    @GetMapping("/recommendation")
    @Operation(summary = "날씨에 따른 추천 조회", description = "위도/경도를 기반으로 현재 날씨와 할 일을 추천합니다.")
    public ResponseEntity<ApiResponse<WeatherRecommendationResponse>> getWeatherAndRecommendation(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        WeatherResponse weather = weatherQueryService.getWeather(lat, lon);
        String main = weather.getCurrent().getWeather().get(0).getMain();
        String desc = weather.getCurrent().getWeather().get(0).getDescription();
        String reco = weatherQueryService.getRecommendation(main);

        WeatherRecommendationResponse response = new WeatherRecommendationResponse(main, desc, reco);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
