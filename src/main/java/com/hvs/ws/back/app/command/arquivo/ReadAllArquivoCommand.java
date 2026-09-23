package com.hvs.ws.back.app.command.arquivo;

public record ReadAllArquivoCommand(ArquivoSearchQuery aArquivoSearchQuery) {

    public static ReadAllArquivoCommand from(final ArquivoSearchQuery aArquivoSearchQuery) {

        return new ReadAllArquivoCommand(aArquivoSearchQuery);
    }
}