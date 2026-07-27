package com.hvs.webstore.back.app.output.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;

public record DeletePrecoPromocionalOutput(Long aId,
                                           String aUuid,
                                           String aMessage) {

    public static DeletePrecoPromocionalOutput from(PrecoPromocional aPrecoPromocional) {

        return new DeletePrecoPromocionalOutput(
                aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                "PrecoPromocional deleted: " + aPrecoPromocional.getUuid().getValue());
    }
}
