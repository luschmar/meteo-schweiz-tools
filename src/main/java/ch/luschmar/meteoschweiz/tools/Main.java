package ch.luschmar.meteoschweiz.tools;

import ch.luschmar.meteoschweiz.tools.transfer.HttpClientInputStreamProvider;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

public class Main {
	public static void main(String[] args) throws Exception {
		var config = Configuration.parse(args);
		var client = HttpClient.newHttpClient();

		var crawler = new DataCrawler(new HttpClientInputStreamProvider(client), config.baseUrl());

		var resultForecast = crawler.fetchWeatherReport("de", "north");
		
		System.out.println(resultForecast);
	}

	private record Configuration(URI baseUrl, int locationId) {

		static Configuration parse(String[] args) throws URISyntaxException {
			if (args.length < 2) {
				throw new IllegalArgumentException("Please specify the seed URL.");
			}
			var baseUrl = new URI(args[0]);
			var plz = Integer.parseInt(args[1]);
			return new Configuration(baseUrl, plz);
		}
	}
}
