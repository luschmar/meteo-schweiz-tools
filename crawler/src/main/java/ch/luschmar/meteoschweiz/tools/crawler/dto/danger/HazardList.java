package ch.luschmar.meteoschweiz.tools.crawler.dto.danger;

import java.util.List;

public record HazardList(List<HazardItem> avalanches, List<HazardItem> snow, List<HazardItem> frost, List<HazardItem> forestfire, List<HazardItem> earthquake) {

}
