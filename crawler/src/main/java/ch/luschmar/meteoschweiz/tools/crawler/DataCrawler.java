package ch.luschmar.meteoschweiz.tools.crawler;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import ch.luschmar.meteoschweiz.tools.crawler.dto.KnownApi;
import ch.luschmar.meteoschweiz.tools.crawler.dto.common.DataWrapper;
import ch.luschmar.meteoschweiz.tools.crawler.dto.danger.Danger;
import ch.luschmar.meteoschweiz.tools.crawler.dto.forecastchart.ForecastChart;
import ch.luschmar.meteoschweiz.tools.crawler.dto.weatherwidget.WeatherWidget;
import ch.luschmar.meteoschweiz.tools.crawler.jackson.LocalDateTimeDeserializer;

public class DataCrawler {
	private static final String VERSION_PATH = "/product/output/versions.json";

	private final InputStreamProvider client;
	private final ObjectMapper objectMapper;

	private final Map<KnownApi, String> versionsCache;
	private final URI versionUrl;
	private final URI baseUrl;

	public DataCrawler(InputStreamProvider client, URI baseUrl) throws URISyntaxException {
		this.client = client;
		this.baseUrl = baseUrl;
		this.objectMapper = configureObjectMapper();
		this.versionsCache = new HashMap<KnownApi, String>();
		this.versionUrl = overwriteUriPath(baseUrl, VERSION_PATH);
	}

	static ObjectMapper configureObjectMapper() {
		var module = new SimpleModule();
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		return JsonMapper.builder()
				.addModule(module)
				.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
				.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true).build();
	}
	
	public void invalidateVersionCache() {
		versionsCache.clear();
	}

	public List<ForecastChart> fetchForecastChart(int plz) {
		try(var inputStream = client.getInputStream(constructVersionedUriWithPlz(KnownApi.FORECAST_CHART, plz))){
			var typeRef = new TypeReference<List<ForecastChart>>() {};
			return objectMapper.readValue(inputStream, typeRef);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public DataWrapper<WeatherWidget> fetchWidget(int plz) {
		try(var inputStream = client.getInputStream(constructVersionedUriWithPlz(KnownApi.WEATHER_WIDGET, plz))){
			var typeRef = new TypeReference<DataWrapper<WeatherWidget>>() {};
			return objectMapper.readValue(inputStream, typeRef);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Danger fetchDanger() {
		try(var inputStream = client.getInputStream(constructVersionedUri(KnownApi.DANGER))){
			return objectMapper.readValue(inputStream, Danger.class);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	Map<KnownApi, String> fetchVersions() {
		try(var inputStream = client.getInputStream(versionUrl)){
			var typeRef = new TypeReference<Map<KnownApi, String>>() {};
			return objectMapper.readValue(inputStream, typeRef);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	private URI constructVersionedUri(KnownApi knwonApi) {
		if (versionsCache.isEmpty()) {
			versionsCache.putAll(fetchVersions());
		}

		var path = knwonApi.getPathPattern().formatted(versionsCache.get(knwonApi));
		return overwriteUriPath(this.baseUrl, path);
	}

	private URI constructVersionedUriWithPlz(KnownApi knwonApi, int plz) {
		if (versionsCache.isEmpty()) {
			versionsCache.putAll(fetchVersions());
		}

		var path = knwonApi.getPathPattern().formatted(versionsCache.get(knwonApi), plz);
		return overwriteUriPath(this.baseUrl, path);
	}

	private static URI overwriteUriPath(URI orginal, String newPath){
		try {
		return new URI(orginal.getScheme(), 
				orginal.getHost(), 
				newPath, 
				orginal.getFragment());
		}catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
