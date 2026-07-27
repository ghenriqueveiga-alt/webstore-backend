package com.hvs.webstore.back.app.output.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;

public record DeleteAnexoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteAnexoOutput from(Anexo aAnexo) {

        return new DeleteAnexoOutput(
                aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                "Anexo deleted: " + aAnexo.getUuid().getValue());
    }
}
