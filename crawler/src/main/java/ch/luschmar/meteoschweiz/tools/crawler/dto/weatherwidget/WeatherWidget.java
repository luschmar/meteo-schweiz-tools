package ch.luschmar.meteoschweiz.tools.crawler.dto.weatherwidget;

public record WeatherWidget(
		int altitude,
		String cityName,
		Current current,
		int weatherSymbolId) {
}
