package ch.luschmar.meteoschweiz.tools.dto.danger;

import ch.luschmar.meteoschweiz.tools.dto.common.Config;

public record Danger(HazardList hazards, Config config) {

}
