package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import java.util.List;

public record WindWrapper(
		List<Wind> data, List<WindSymbol> symbols) {

}
