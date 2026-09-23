package com.hvs.ws.back.app.output.arquivo;

import com.hvs.ws.back.domain.entity.arquivo.Arquivo;

public record DeleteArquivoOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static DeleteArquivoOutput from(final Arquivo aArquivo) {

        return new DeleteArquivoOutput(
                aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                "The Archive with id: " + aArquivo.getUuid().getValue() + " has been successfully deleted.");
    }
}
