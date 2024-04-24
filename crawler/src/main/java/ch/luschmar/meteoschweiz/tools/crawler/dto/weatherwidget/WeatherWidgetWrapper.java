package ch.luschmar.meteoschweiz.tools.crawler.dto.weatherwidget;

import ch.luschmar.meteoschweiz.tools.crawler.dto.WithLocationId;
import ch.luschmar.meteoschweiz.tools.crawler.dto.common.Config;

public record WeatherWidgetWrapper(WeatherWidget data, Config config, int locationId) implements WithLocationId<WeatherWidgetWrapper> {
	@Override
	public WeatherWidgetWrapper withLocationId(int locationId) {
		return new WeatherWidgetWrapper(data(), config(), locationId);
	}
}
