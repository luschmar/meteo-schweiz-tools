package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public record Temperature(LocalDateTime timestamp,
						  float celsius) {

}
