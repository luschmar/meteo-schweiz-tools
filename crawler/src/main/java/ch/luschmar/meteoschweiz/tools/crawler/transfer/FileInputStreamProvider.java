package ch.luschmar.meteoschweiz.tools.crawler.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import ch.luschmar.meteoschweiz.tools.crawler.InputStreamProvider;

public class FileInputStreamProvider implements InputStreamProvider{

	@Override
	public InputStream getInputStream(URI resource) throws IOException, InterruptedException {
		return this.getClass().getResourceAsStream(resource.getPath());
	}

}
