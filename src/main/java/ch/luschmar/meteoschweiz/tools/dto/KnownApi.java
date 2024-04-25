package ch.luschmar.meteoschweiz.tools.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum KnownApi {
	@JsonProperty("forecast-chart")
	FORECAST_CHART("/product/output/forecast-chart/version__%s/de/%d.json"),
	@JsonProperty("weather-widget/forecast")
	WEATHER_WIDGET("/product/output/weather-widget/forecast/version__%s/de/%d.json"),
	@JsonProperty("danger")
	DANGER("/product/output/danger/version__%s/de/dangers.json"),
	@JsonProperty("forecast-map")
	FORECAST_MAP("/product/output/forecast-map/version__%s/de/chmap_current.json"),
	@JsonEnumDefaultValue
	UNKNOWN(null);

	private final String pathPattern;

	KnownApi(String pathPattern) {
		this.pathPattern = pathPattern;
	}

	public String getPathPattern() {
		return pathPattern;
	}

}
