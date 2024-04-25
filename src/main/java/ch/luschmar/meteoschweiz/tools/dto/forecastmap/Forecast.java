package ch.luschmar.meteoschweiz.tools.dto.forecastmap;

public record Forecast(String date,
					   String weekday,
					   String weatherDescription,
					   int tempHigh,
					   int tempLow,
					   String weatherSymbolId) {

}
