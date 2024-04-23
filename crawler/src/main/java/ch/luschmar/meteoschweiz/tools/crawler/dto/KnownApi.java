package ch.luschmar.meteoschweiz.tools.crawler.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum KnownApi {
	@JsonProperty("forecast-chart")
	FORECAST_CHART("/product/output/forecast-chart/version__%s/de/%d00.json"),
	@JsonProperty("weather-widget/forecast")
	WEATHER_WIDGET("/product/output/weather-widget/forecast/version__%s/de/%d00.json"),
	@JsonProperty("danger")
	DANGER("/product/output/danger/version__%s/de/dangers.json"),
	@JsonEnumDefaultValue
	UNKNWON(null);
	
	private String pathPattern;

	KnownApi(String pathPattern) {
		this.pathPattern = pathPattern;
	}
	
	public String getPathPattern() {
		return pathPattern;
	}
	
}
