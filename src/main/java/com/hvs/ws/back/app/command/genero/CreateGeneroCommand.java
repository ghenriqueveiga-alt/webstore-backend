package com.hvs.ws.back.app.command.genero;

public record CreateGeneroCommand(String aNome,
                                  String aDescricao) {

    public static CreateGeneroCommand from(final String aNome,
                                           final String aDescricao) {

        return new CreateGeneroCommand(
                aNome,
                aDescricao);
    }
}
