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
	@JsonProperty("weather-report/de/north")
	WEATHER_REPORT_DE_NORTH("/product/output/weather-report/de/north/version__%s/textproduct_de.xhtml"),
	@JsonProperty("weather-report/de/south")
	WEATHER_REPORT_DE_SOUTH("/product/output/weather-report/de/south/version__%s/textproduct_de.xhtml"),
	@JsonProperty("weather-report/de/west")
	WEATHER_REPORT_DE_WEST("/product/output/weather-report/de/west/version__%s/textproduct_de.xhtml"),
	@JsonProperty("weather-report/fr/north")
	WEATHER_REPORT_FR_NORTH("/product/output/weather-report/fr/north/version__%s/textproduct_fr.xhtml"),
	@JsonProperty("weather-report/fr/south")
	WEATHER_REPORT_FR_SOUTH("/product/output/weather-report/fr/south/version__%s/textproduct_fr.xhtml"),
	@JsonProperty("weather-report/fr/west")
	WEATHER_REPORT_FR_WEST("/product/output/weather-report/fr/west/version__%s/textproduct_fr.xhtml"),
	@JsonProperty("weather-report/it/north")
	WEATHER_REPORT_IT_NORTH("/product/output/weather-report/it/north/version__%s/textproduct_it.xhtml"),
	@JsonProperty("weather-report/it/south")
	WEATHER_REPORT_IT_SOUTH("/product/output/weather-report/it/south/version__%s/textproduct_it.xhtml"),
	@JsonProperty("weather-report/it/west")
	WEATHER_REPORT_IT_WEST("/product/output/weather-report/it/west/version__%s/textproduct_it.xhtml"),
	
	
	@JsonEnumDefaultValue
	UNKNOWN(null);

	private final String pathPattern;

	KnownApi(String pathPattern) {
		this.pathPattern = pathPattern;
	}

	public String getPathPattern() {
		return pathPattern;
	}

	public static KnownApi selectWeatherReport(String lang, String location) {
		if(lang.equalsIgnoreCase("de")) {
			if(location.equalsIgnoreCase("north")) {
				return WEATHER_REPORT_DE_NORTH;
			} else if (location.equalsIgnoreCase("south")) {
				return WEATHER_REPORT_DE_SOUTH;
			} else if (location.equalsIgnoreCase("west")) {
				return WEATHER_REPORT_DE_WEST;
			}
		} else if(lang.equalsIgnoreCase("fr")) {
			if(location.equalsIgnoreCase("north")) {
				return WEATHER_REPORT_FR_NORTH;
			} else if (location.equalsIgnoreCase("south")) {
				return WEATHER_REPORT_FR_SOUTH;
			} else if (location.equalsIgnoreCase("west")) {
				return WEATHER_REPORT_FR_WEST;
			}
		} else if(lang.equalsIgnoreCase("it")) {
			if(location.equalsIgnoreCase("north")) {
				return WEATHER_REPORT_IT_NORTH;
			} else if (location.equalsIgnoreCase("south")) {
				return WEATHER_REPORT_IT_SOUTH;
			} else if (location.equalsIgnoreCase("west")) {
				return WEATHER_REPORT_IT_WEST;
			}
		}
		throw new IllegalArgumentException("unknown language location combination; no weather-report found");
	}

}
