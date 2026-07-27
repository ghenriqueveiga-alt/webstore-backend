package com.hvs.webstore.back.app.output.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;

public record UpdatePrecoPromocionalOutput(Long aId,
                                           String aUuid,
                                           String aMessage) {

    public static UpdatePrecoPromocionalOutput from(PrecoPromocional aPrecoPromocional) {

        return new UpdatePrecoPromocionalOutput(
                aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                "PrecoPromocional updated: " + aPrecoPromocional.getUuid().getValue());
    }
}
