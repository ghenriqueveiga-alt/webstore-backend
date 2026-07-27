package com.hvs.webstore.back.app.command.television.arquivo;

public record PatchArquivoCommand(Long aId,
                                  String aUuid,
                                  String aStatusDesc,
                                  String aNome,
                                  String aTipo,
                                  Long aTamanho,
                                  String aCaminho,
                                  String aDuracao) {

    public static PatchArquivoCommand from(final Long aId,
                                           final PatchArquivoCommand aInput) {

        return new PatchArquivoCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aTipo,
                aInput.aTamanho,
                aInput.aCaminho,
                aInput.aDuracao);
    }

    public static PatchArquivoCommand from(final String aUuid,
                                           final PatchArquivoCommand aInput) {

        return new PatchArquivoCommand(
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