package ch.luschmar.meteoschweiz.tools.crawler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;

import org.junit.jupiter.api.Test;

import ch.luschmar.meteoschweiz.tools.crawler.dto.KnownApi;

public class DataCrawlerTest {
	@Test
	void testVersion() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchVersions();

		assertTrue(EnumSet.allOf(KnownApi.class).containsAll(data.keySet()));
	}
	
	@Test
	void fetchDanger() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchDanger();
	}
	
	@Test
	void fetchForecastChart() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchForecastChart(8001);
	}
	
	@Test
	void fetchWidget() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchWidget(8001);
	}
}
