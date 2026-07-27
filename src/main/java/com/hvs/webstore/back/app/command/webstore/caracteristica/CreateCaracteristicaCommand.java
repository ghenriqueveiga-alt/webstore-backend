package com.hvs.webstore.back.app.command.webstore.caracteristica;

public record CreateCaracteristicaCommand(String aNome,
                                          String aDescricao) {

    public static CreateCaracteristicaCommand from(final String aNome,
                                                   final String aDescricao) {

        return new CreateCaracteristicaCommand(
                aNome,
                aDescricao);
    }
}
