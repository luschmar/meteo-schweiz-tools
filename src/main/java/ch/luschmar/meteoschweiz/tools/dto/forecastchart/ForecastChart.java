package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import java.time.LocalDateTime;
import java.util.List;

public record ForecastChart(LocalDateTime currentTime, 
		String currentTimeString, 
		String dayString,
		LocalDateTime maxDate, 
		LocalDateTime minDate, 
		LocalDateTime newDay, 
		LocalDateTime sunrise, 
		LocalDateTime sunset,
		Symbol symbolDay,
		List<Rainfall> rainfall,
		List<Sunshine> sunshine,
		List<Variance> varianceRain,
		List<Variance> windGustSpeedVariance,
		List<Variance> windSpeedVariance,
		List<Variance> varianceRange,
		List<Temperature> temperature,
		//List<WindWrapper> windGustPeak,
		//List<WindWrapper> wind,
		List<Symbol> symbols) {
}
