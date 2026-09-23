package com.hvs.ws.back.app.command.canal;

public record CreateCanalCommand(String aNome,
                                 String aDescricao,
                                 String aLogotipoUrl,
                                 String aSite) {

    public static CreateCanalCommand from(final String aNome,
                                          final String aDescricao,
                                          final String aLogotipoUrl,
                                          final String aSite) {

        return new CreateCanalCommand(
                aNome,
                aDescricao,
                aLogotipoUrl,
                aSite);
    }
}
