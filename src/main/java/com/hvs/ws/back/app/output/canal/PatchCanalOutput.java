package com.hvs.ws.back.app.output.canal;

import com.hvs.ws.back.domain.entity.canal.Canal;

public record PatchCanalOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchCanalOutput from(final Canal aCanal) {

        return new PatchCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                "The Canal with id: " + aCanal.getUuid().getValue() + " has been successfully patched.");
    }
}
