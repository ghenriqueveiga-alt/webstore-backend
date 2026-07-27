package com.hvs.webstore.back.app.output.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;

public record CreateAnexoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateAnexoOutput from(Anexo aAnexo) {

        return new CreateAnexoOutput(
                aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                "Anexo created: " + aAnexo.getUuid().getValue());
    }
}
