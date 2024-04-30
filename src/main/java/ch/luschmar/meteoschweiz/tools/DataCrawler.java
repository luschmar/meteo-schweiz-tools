package ch.luschmar.meteoschweiz.tools;

import ch.luschmar.meteoschweiz.tools.dto.KnownApi;
import ch.luschmar.meteoschweiz.tools.dto.danger.Danger;
import ch.luschmar.meteoschweiz.tools.dto.forecastchart.ForecastChartWrapper;
import ch.luschmar.meteoschweiz.tools.dto.forecastmap.ForecastMap;
import ch.luschmar.meteoschweiz.tools.dto.weatherreport.Content;
import ch.luschmar.meteoschweiz.tools.dto.weatherreport.Text;
import ch.luschmar.meteoschweiz.tools.dto.weatherreport.Title;
import ch.luschmar.meteoschweiz.tools.dto.weatherwidget.WeatherWidgetWrapper;
import ch.luschmar.meteoschweiz.tools.jackson.LocalDateTimeDeserializer;
import ch.luschmar.meteoschweiz.tools.transfer.InputStreamProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class DataCrawler {
	private static final String VERSION_PATH = "/product/output/versions.json";

	private final InputStreamProvider client;
	private final ObjectMapper objectMapper;

	private final Map<KnownApi, String> versionsCache;
	private final URI versionUrl;
	private final URI baseUrl;

	public DataCrawler(InputStreamProvider client, URI baseUrl) {
		this.client = client;
		this.baseUrl = baseUrl;
		this.objectMapper = configureObjectMapper();
		this.versionsCache = new EnumMap<>(KnownApi.class);
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

	public ForecastChartWrapper fetchForecastChart(int locationId) {
		try (var inputStream = client.getInputStream(buildVersionedUrlWithLocationId(KnownApi.FORECAST_CHART, locationId))) {
			return objectMapper.readValue(inputStream, ForecastChartWrapper.class).withLocationId(locationId);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Content> fetchWeatherReport(String lang, String location) {
		try (var inputStream = client.getInputStream(buildVersionedUrl(KnownApi.selectWeatherReport(lang, location)))) {
			try (var bufferedInputStream = new BufferedInputStream(inputStream)){
				var doc = Jsoup.parseBodyFragment(new String(bufferedInputStream.readAllBytes()));
				return doc.body().getElementsByTag("div").stream().flatMap(a -> a.childNodes().stream()).map(this::toContent).filter(Objects::nonNull).toList();
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private Content toContent(Node n) {
	
		if(n instanceof Element e) {
			if(e.tagName().equalsIgnoreCase("p")) {
				return new Text(e.text());
			}
			return new Title(e.text());
		}
		return null;
	}

	
	public List<ForecastMap> fetchForecastMap() {
		try (var inputStream = client.getInputStream(buildVersionedUrl(KnownApi.FORECAST_MAP))) {
			var typeRef = new TypeReference<List<ForecastMap>>() {
			};
			return objectMapper.readValue(inputStream, typeRef);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public WeatherWidgetWrapper fetchWidget(int locationId) {
		try (var inputStream = client.getInputStream(buildVersionedUrlWithLocationId(KnownApi.WEATHER_WIDGET, locationId))) {
			return objectMapper.readValue(inputStream, WeatherWidgetWrapper.class).withLocationId(locationId);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Danger fetchDanger() {
		try (var inputStream = client.getInputStream(buildVersionedUrl(KnownApi.DANGER))) {
			return objectMapper.readValue(inputStream, Danger.class);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	Map<KnownApi, String> fetchVersions() {
		try (var inputStream = client.getInputStream(versionUrl)) {
			var typeRef = new TypeReference<Map<KnownApi, String>>() {
			};
			return objectMapper.readValue(inputStream, typeRef);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private URI buildVersionedUrl(KnownApi knownApi) {
		if (versionsCache.isEmpty()) {
			versionsCache.putAll(fetchVersions());
		}

		var path = knownApi.getPathPattern().formatted(versionsCache.get(knownApi));
		return overwriteUriPath(this.baseUrl, path);
	}

	private URI buildVersionedUrlWithLocationId(KnownApi knownApi, int locationId) {
		if (versionsCache.isEmpty()) {
			versionsCache.putAll(fetchVersions());
		}

		var path = knownApi.getPathPattern().formatted(versionsCache.get(knownApi), locationId);
		return overwriteUriPath(this.baseUrl, path);
	}

	private static URI overwriteUriPath(URI original, String newPath) {
		try {
			return new URI(original.getScheme(),
					original.getHost(),
					newPath,
					original.getFragment());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
