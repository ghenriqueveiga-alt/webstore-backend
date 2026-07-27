package com.hvs.webstore.back.app.output.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;

public record PatchAnexoOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchAnexoOutput from(Anexo aAnexo) {

        return new PatchAnexoOutput(
                aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                "Anexo patched: " + aAnexo.getUuid().getValue());
    }
}
