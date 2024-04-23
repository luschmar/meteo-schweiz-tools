package ch.luschmar.meteoschweiz.tools.crawler.jackson;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class LocalDateTimeDeserializer extends StdScalarDeserializer<LocalDateTime> {
	public LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JacksonException {
		var longValue = parser.readValueAs(Long.class);
		
		if(longValue > Integer.MAX_VALUE) {
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneOffset.systemDefault());
		}
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(longValue), ZoneOffset.systemDefault());

	}
}