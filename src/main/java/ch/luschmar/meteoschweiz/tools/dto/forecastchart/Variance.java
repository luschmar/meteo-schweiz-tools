package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public record Variance(LocalDateTime timestamp, float min, float max) {

}
