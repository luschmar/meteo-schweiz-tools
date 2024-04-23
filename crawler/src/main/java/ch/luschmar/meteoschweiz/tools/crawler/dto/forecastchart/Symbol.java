package ch.luschmar.meteoschweiz.tools.crawler.dto.forecastchart;

import java.time.LocalDateTime;

public record Symbol(int weatherSymbolId, 
		LocalDateTime timestamp) {

}
