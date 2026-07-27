package com.hvs.webstore.back.app.output.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;

public record PatchPrecoPromocionalOutput(Long aId,
                                          String aUuid,
                                          String aMessage) {

    public static PatchPrecoPromocionalOutput from(PrecoPromocional aPrecoPromocional) {

        return new PatchPrecoPromocionalOutput(
                aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                "PrecoPromocional patched: " + aPrecoPromocional.getUuid().getValue());
    }
}
