package com.hvs.ws.back.app.command.arquivo;

public record ReadArquivoCommand(Long aId,
                                 String aUuid){

    public static ReadArquivoCommand from(final Long aId) {

        return new ReadArquivoCommand(aId, null);
    }

    public static ReadArquivoCommand from(final String aUuid) {

        return new ReadArquivoCommand(null, aUuid);
    }
}