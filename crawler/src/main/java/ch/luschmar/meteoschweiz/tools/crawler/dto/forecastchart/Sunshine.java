package ch.luschmar.meteoschweiz.tools.crawler.dto.forecastchart;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public record Sunshine(LocalDateTime timestamp, int amount) {

}
