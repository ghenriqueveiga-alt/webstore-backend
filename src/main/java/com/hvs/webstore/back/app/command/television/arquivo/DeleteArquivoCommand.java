package com.hvs.webstore.back.app.command.television.arquivo;

public record DeleteArquivoCommand(Long aId,
                                   String aUuid) {

    public static DeleteArquivoCommand from(final Long aId) {

        return new DeleteArquivoCommand(aId, null);
    }

    public static DeleteArquivoCommand from(final String aUuid) {

        return new DeleteArquivoCommand(null, aUuid);
    }
}