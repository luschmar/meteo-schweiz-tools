package ch.luschmar.meteoschweiz.tools.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public interface InputStreamProvider {
	InputStream getInputStream(URI resource) throws IOException, InterruptedException;
}
