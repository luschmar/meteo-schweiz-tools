package ch.luschmar.meteoschweiz.tools.crawler.dto.weatherwidget;

import ch.luschmar.meteoschweiz.tools.crawler.dto.WithLocationId;

public record WeatherWidget(
		int locationId,
		int altitude,
		String cityName,
		Current current,
		int weatherSymbolId) implements WithLocationId<WeatherWidget> {

	@Override
	public WeatherWidget withLocationId(int locationId) {
		return new WeatherWidget(locationId, altitude, cityName, current, weatherSymbolId);
	}
}
