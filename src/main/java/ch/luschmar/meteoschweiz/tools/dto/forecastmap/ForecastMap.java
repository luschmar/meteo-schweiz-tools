package ch.luschmar.meteoschweiz.tools.dto.forecastmap;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;
import java.util.List;

public record ForecastMap(@JsonAlias("warnRegionIds")
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
