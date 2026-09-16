package com.hvs.webstore.back.infra.api.television;

import org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/time")
public class TimeApiController {

    @GetMapping
    public Map<String, Object> now() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        return Map.of(
            "now", now.toOffsetDateTime().toString(),
            "hours", now.getHour(),
            "minutes", now.getMinute(),
            "dayOfWeek", now.getDayOfWeek().getValue()
        );
    }
}
