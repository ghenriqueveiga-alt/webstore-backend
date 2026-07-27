package com.hvs.webstore.back.app.output.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;

public record UpdateAnexoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdateAnexoOutput from(Anexo aAnexo) {

        return new UpdateAnexoOutput(
                aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                "Anexo updated: " + aAnexo.getUuid().getValue());
    }
}
