package com.hvs.webstore.back.app.output.television.canal;

import com.hvs.webstore.back.domain.entity.television.canal.Canal;

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
