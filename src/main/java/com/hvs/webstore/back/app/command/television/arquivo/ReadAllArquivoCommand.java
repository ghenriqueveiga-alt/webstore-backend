package com.hvs.webstore.back.app.command.television.arquivo;

public record ReadAllArquivoCommand(ArquivoSearchQuery aArquivoSearchQuery) {

    public static ReadAllArquivoCommand from(final ArquivoSearchQuery aArquivoSearchQuery) {

        return new ReadAllArquivoCommand(aArquivoSearchQuery);
    }
}