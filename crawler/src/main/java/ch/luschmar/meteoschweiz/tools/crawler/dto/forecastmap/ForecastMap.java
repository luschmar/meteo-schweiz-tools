package ch.luschmar.meteoschweiz.tools.crawler.dto.forecastmap;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ForecastMap(
		@JsonAlias("warnRegionIds")
		List<Integer> warnRegionIds, 
		int altitude, 
		String cityName,
		String locationName,
		String locationPath,
		String coordX,
		String coordY,
		int locationId,
		String locationType,
		LocalDateTime timestamp,
		List<Forecast> forecasts
		) {

}
