package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import java.time.LocalDateTime;

public record WindSymbol(LocalDateTime timestamp,
						 String symbolId) {

}
