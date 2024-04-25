package ch.luschmar.meteoschweiz.tools.dto.forecastchart;

import ch.luschmar.meteoschweiz.tools.dto.WithLocationId;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public record ForecastChartWrapper(List<ForecastChart> list,
								   int locationId) implements WithLocationId<ForecastChartWrapper> {
	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public ForecastChartWrapper(List<ForecastChart> list) {
		this(list, -1);
	}

	@Override
	public ForecastChartWrapper withLocationId(int locationId) {
		return new ForecastChartWrapper(list(), locationId);
	}
}
