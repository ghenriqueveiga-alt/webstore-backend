package com.hvs.webstore.back.app.command.webstore.anexo;

public record PatchAnexoCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String aEntidadeNome,
                                Long aEntidadeId,
                                String aNome,
                                String aTipo,
                                Long aTamanho,
                                String aUrl) {

    public static PatchAnexoCommand from(final Long aId,
                                         final PatchAnexoCommand aIn) {

        return new PatchAnexoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aEntidadeNome,
                aIn.aEntidadeId,
                aIn.aNome,
                aIn.aTipo,
                aIn.aTamanho,
                aIn.aUrl);
    }

    public static PatchAnexoCommand from(final String aUuid,
                                         final PatchAnexoCommand aIn) {

        return new PatchAnexoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aEntidadeNome,
                aIn.aEntidadeId,
                aIn.aNome,
                aIn.aTipo,
                aIn.aTamanho,
                aIn.aUrl);
    }
}
