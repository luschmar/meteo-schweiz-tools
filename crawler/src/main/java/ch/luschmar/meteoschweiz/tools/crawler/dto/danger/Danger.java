package ch.luschmar.meteoschweiz.tools.crawler.dto.danger;

import ch.luschmar.meteoschweiz.tools.crawler.dto.common.Config;

public record Danger(HazardList hazards, Config config) {

}
