package com.hvs.ws.back.app.command.arquivo;

public record DeleteArquivoCommand(Long aId,
                                   String aUuid) {

    public static DeleteArquivoCommand from(final Long aId) {

        return new DeleteArquivoCommand(aId, null);
    }

    public static DeleteArquivoCommand from(final String aUuid) {

        return new DeleteArquivoCommand(null, aUuid);
    }
}