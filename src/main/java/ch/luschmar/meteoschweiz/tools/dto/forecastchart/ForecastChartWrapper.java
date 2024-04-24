package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import ch.luschmar.meteoschweiz.tools.dto.WithLocationId;

public record ForecastChartWrapper(List<ForecastChart> list, int locationId) implements WithLocationId<ForecastChartWrapper> {
	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public ForecastChartWrapper(List<ForecastChart> list) {
		this(list, 0);
	}

	@Override
	public ForecastChartWrapper withLocationId(int locationId) {
		return new ForecastChartWrapper(list(), locationId);
	}
}
