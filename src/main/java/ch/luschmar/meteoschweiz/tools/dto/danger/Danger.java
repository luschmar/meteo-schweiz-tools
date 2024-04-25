package ch.luschmar.meteoschweiz.tools.dto.danger;

import ch.luschmar.meteoschweiz.tools.dto.common.Config;

import java.util.List;

public record Danger(HazardList hazards,
					 List<DangerItem> airfields,
					 List<DangerItem> lakes,
					 Config config) {

}
