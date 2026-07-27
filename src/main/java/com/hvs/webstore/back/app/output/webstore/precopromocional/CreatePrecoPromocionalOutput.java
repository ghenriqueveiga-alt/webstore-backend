package com.hvs.webstore.back.app.output.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;

public record CreatePrecoPromocionalOutput(Long aId,
                                           String aUuid,
                                           String aMessage) {

    public static CreatePrecoPromocionalOutput from(PrecoPromocional aPrecoPromocional) {

        return new CreatePrecoPromocionalOutput(
                aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                "PrecoPromocional created: " + aPrecoPromocional.getUuid().getValue());
    }
}
