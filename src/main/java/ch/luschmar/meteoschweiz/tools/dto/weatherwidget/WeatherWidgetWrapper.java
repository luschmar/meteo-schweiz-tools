package ch.luschmar.meteoschweiz.tools.dto.weatherwidget;

import ch.luschmar.meteoschweiz.tools.dto.WithLocationId;
import ch.luschmar.meteoschweiz.tools.dto.common.Config;

public record WeatherWidgetWrapper(WeatherWidget data,
								   Config config,
								   int locationId) implements WithLocationId<WeatherWidgetWrapper> {
	@Override
	public WeatherWidgetWrapper withLocationId(int locationId) {
		return new WeatherWidgetWrapper(data(), config(), locationId);
	}
}
