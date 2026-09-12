package com.hvs.webstore.back.app.command.television.arquivo;

public record PatchArquivoCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aNome,
                                  String aTipoCode,
                                  Long aTamanho,
                                  String aCaminho) {

    public static PatchArquivoCommand from(final Long aId,
                                           final PatchArquivoCommand aInput) {

        return new PatchArquivoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aTamanho,
                aInput.aCaminho);
    }

    public static PatchArquivoCommand from(final String aUuid,
                                           final PatchArquivoCommand aInput) {

        return new PatchArquivoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aTipoCode,
                aInput.aTamanho,
                aInput.aCaminho);
    }
}
