package com.hvs.webstore.back.app.command.television.arquivo;

public record UpdateArquivoCommand(Long aId,
                                   String aUuid,
                                   String aStatusDesc,
                                   String aNome,
                                   String aTipo,
                                   Long aTamanho,
                                   String aCaminho,
                                   String aDuracao) {

    public static UpdateArquivoCommand from(final Long aId,
                                            final UpdateArquivoCommand aInput) {

        return new UpdateArquivoCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aTipo,
                aInput.aTamanho,
                aInput.aCaminho,
                aInput.aDuracao);
    }

    public static UpdateArquivoCommand from(final String aUuid,
                                            final UpdateArquivoCommand aInput) {

        return new UpdateArquivoCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aTipo,
                aInput.aTamanho,
                aInput.aCaminho,
                aInput.aDuracao);
    }
}