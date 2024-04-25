package ch.luschmar.meteoschweiz.tools.dto.danger;

import java.time.LocalDateTime;
import java.util.List;

public record DangerItem(int warnlevel,
						 LocalDateTime expires,
						 List<Integer> areas,
						 WarnType warnType,
						 String description) {

}
