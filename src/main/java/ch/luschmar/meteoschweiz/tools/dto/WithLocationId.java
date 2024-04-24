package ch.luschmar.meteoschweiz.tools.dto;

public interface WithLocationId<T> {
	int locationId();
	
	T withLocationId(int locationId);
}
