package com.hvs.webstore.back.app.command.television.arquivo;

public record UpdateArquivoCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   String aNome,
                                   String aTipoCode,
                                   Long aTamanho,
                                   String aCaminho) {

    public static UpdateArquivoCommand from(final Long aId,
                                            final UpdateArquivoCommand aInput) {

        return new UpdateArquivoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aTamanho,
                aInput.aCaminho);
    }

    public static UpdateArquivoCommand from(final String aUuid,
                                            final UpdateArquivoCommand aInput) {

        return new UpdateArquivoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aTamanho,
                aInput.aCaminho);
    }
}
