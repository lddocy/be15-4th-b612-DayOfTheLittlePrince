package littleprince.weather.query.mapper;

import littleprince.weather.query.dto.WeatherDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeatherMapper {
    WeatherDTO findByWeatherMain(String weatherMain);
}
