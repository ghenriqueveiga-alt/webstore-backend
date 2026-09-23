package com.hvs.ws.back.app.output.arquivo;

import com.hvs.ws.back.domain.entity.arquivo.Arquivo;

public record PatchArquivoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static PatchArquivoOutput from(final Arquivo aArquivo) {

        return new PatchArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                "The Archive with id: " + aArquivo.getUuid().getValue() + " has been successfully patched.");
    }
}
