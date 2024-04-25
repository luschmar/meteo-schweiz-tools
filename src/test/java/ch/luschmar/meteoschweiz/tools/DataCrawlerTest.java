package ch.luschmar.meteoschweiz.tools;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;

import org.junit.jupiter.api.Test;

import ch.luschmar.meteoschweiz.tools.DataCrawler;
import ch.luschmar.meteoschweiz.tools.dto.KnownApi;
import ch.luschmar.meteoschweiz.tools.transfer.FileInputStreamProvider;

class DataCrawlerTest {
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
		System.out.println(data);
	}
	
	@Test
	void fetchForecastChart() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchForecastChart(8001_00);
		System.out.println(data);
	}
	
	@Test
	void fetchWidget() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchWidget(8001_00);
		System.out.println(data);
	}
	
	@Test
	void fetchForecastMap() throws URISyntaxException {
		var crawler = new DataCrawler(new FileInputStreamProvider(), URI.create("http://example.com"));
		
		var data = crawler.fetchForecastMap();
		
		System.out.println(data.stream().filter(a -> a.locationId() == 8001_00).toList());
	}
}
