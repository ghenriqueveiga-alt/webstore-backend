package com.hvs.webstore.back.app.command.television.arquivo;

public record ReadArquivoCommand(Long aId,
                                 String aUuid){

    public static ReadArquivoCommand from(final Long aId) {

        return new ReadArquivoCommand(aId, null);
    }

    public static ReadArquivoCommand from(final String aUuid) {

        return new ReadArquivoCommand(null, aUuid);
    }
}