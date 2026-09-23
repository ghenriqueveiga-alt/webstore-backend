package com.hvs.ws.back.app.output.arquivo;

import com.hvs.ws.back.domain.entity.arquivo.Arquivo;

public record CreateArquivoOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static CreateArquivoOutput from(final Arquivo aArquivo) {

        return new CreateArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                "The Archive with id: " + aArquivo.getUuid().getValue() + " has been successfully created.");
    }
}
