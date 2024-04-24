package ch.luschmar.meteoschweiz.tools.dto.danger;

import java.util.List;

public record HazardList(List<DangerItem> avalanches, List<DangerItem> snow, List<DangerItem> frost, List<DangerItem> forestfire, List<DangerItem> earthquake) {

}
