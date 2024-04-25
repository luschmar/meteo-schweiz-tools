package ch.luschmar.meteoschweiz.tools.dto.weatherwidget;

import ch.luschmar.meteoschweiz.tools.dto.WithLocationId;

import java.time.LocalDateTime;
import java.util.List;

public record WeatherWidget(
		int locationId,
		int altitude,
		String cityName,
		Current current,
		int weatherSymbolId,
		LocalDateTime timestamp,
		List<Forecast> forecasts) implements WithLocationId<WeatherWidget> {

	@Override
	public WeatherWidget withLocationId(int locationId) {
		return new WeatherWidget(locationId, altitude(), cityName(), current(), weatherSymbolId(), timestamp(), forecasts());
	}
}
