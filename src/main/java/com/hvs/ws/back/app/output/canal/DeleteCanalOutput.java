package com.hvs.ws.back.app.output.canal;

import com.hvs.ws.back.domain.entity.canal.Canal;

public record DeleteCanalOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteCanalOutput from(final Canal aCanal) {

        return new DeleteCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                "The Canal with id: " + aCanal.getUuid().getValue() + " has been successfully deleted.");
    }
}
