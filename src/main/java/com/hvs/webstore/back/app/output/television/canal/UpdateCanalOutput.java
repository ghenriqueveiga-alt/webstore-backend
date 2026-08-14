package com.hvs.webstore.back.app.output.television.canal;

import com.hvs.webstore.back.domain.entity.television.canal.Canal;

public record UpdateCanalOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdateCanalOutput from(final Canal aCanal) {

        return new UpdateCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                "The Canal with id: " + aCanal.getUuid().getValue() + " has been successfully updated.");
    }
}
