package com.hvs.ws.back.app.output.canal;

import com.hvs.ws.back.domain.entity.canal.Canal;

public record CreateCanalOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateCanalOutput from(final Canal aCanal) {

        return new CreateCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                "The Canal with id: " + aCanal.getUuid().getValue() + " has been successfully created.");
    }
}
