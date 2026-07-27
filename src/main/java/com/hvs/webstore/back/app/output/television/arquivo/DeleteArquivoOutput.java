package com.hvs.webstore.back.app.output.television.arquivo;

import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;

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
