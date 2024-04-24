package ch.luschmar.meteoschweiz.tools.crawler.dto;

public interface WithLocationId<T> {
	int locationId();
	
	T withLocationId(int locationId);
}
