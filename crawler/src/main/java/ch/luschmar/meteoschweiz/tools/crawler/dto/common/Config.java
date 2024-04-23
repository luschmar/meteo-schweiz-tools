package ch.luschmar.meteoschweiz.tools.crawler.dto.common;

import java.time.LocalDateTime;

public record Config(String name, 
		String language, 
		String version, 
		LocalDateTime timestamp) {

}
