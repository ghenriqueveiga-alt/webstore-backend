package com.hvs.webstore.back.app.command.television.genero;

public record CreateGeneroCommand(String aNome,
                                  String aDescricao) {

    public static CreateGeneroCommand from(final String aNome,
                                           final String aDescricao) {

        return new CreateGeneroCommand(
                aNome,
                aDescricao);
    }
}
