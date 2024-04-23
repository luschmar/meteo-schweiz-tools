package ch.luschmar.meteoschweiz.tools.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientInputStreamProvider implements InputStreamProvider {

	public final HttpClient client;

	public HttpClientInputStreamProvider(HttpClient client) {
		this.client = client;
	}

	@Override
	public InputStream getInputStream(URI resource) throws IOException, InterruptedException {
		var request = HttpRequest //
				.newBuilder(resource) //
				.GET() //
				.build();
		return client.send(request, BodyHandlers.ofInputStream()).body();

	}
}
