package ch.luschmar.meteoschweiz.tools.dto.danger;

import java.util.List;

import ch.luschmar.meteoschweiz.tools.dto.common.Config;

public record Danger(HazardList hazards, List<DangerItem> airfields, List<DangerItem> lakes, Config config) {

}
