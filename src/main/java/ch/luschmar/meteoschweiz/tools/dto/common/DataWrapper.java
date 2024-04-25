package ch.luschmar.meteoschweiz.tools.dto.common;

public record DataWrapper<T>(T data,
							 Config config) {

}
