package ch.luschmar.meteoschweiz.tools.dto.weatherwidget;

import java.time.LocalDateTime;

public record Forecast(LocalDateTime noon, int tempHigh, String weekday, int tempLow, float precipMean, int weatherSymbolId) {

}
