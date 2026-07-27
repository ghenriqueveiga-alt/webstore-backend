package com.hvs.webstore.back.app.command.webstore.anexo;

import java.time.Instant;

public record UpdateAnexoCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aEntidadeNome,
                                 Long aEntidadeId,
                                 String aNome,
                                 String aTipo,
                                 Long aTamanho,
                                 String aUrl,
                                 Instant aDataUpload) {

    public static UpdateAnexoCommand from(final Long aId,
                                          final UpdateAnexoCommand aIn) {

        return new UpdateAnexoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aEntidadeNome,
                aIn.aEntidadeId,
                aIn.aNome,
                aIn.aTipo,
                aIn.aTamanho,
                aIn.aUrl,
                aIn.aDataUpload
        );
    }

    public static UpdateAnexoCommand from(final String aUuid,
                                          final UpdateAnexoCommand aIn) {

        return new UpdateAnexoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aEntidadeNome,
                aIn.aEntidadeId,
                aIn.aNome,
                aIn.aTipo,
                aIn.aTamanho,
                aIn.aUrl,
                aIn.aDataUpload
        );
    }
}
