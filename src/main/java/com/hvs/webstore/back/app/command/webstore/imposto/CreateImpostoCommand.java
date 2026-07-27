package com.hvs.webstore.back.app.command.webstore.imposto;

public record CreateImpostoCommand(String aNome,
                                   String aTipoCode,
                                   Integer aAliquota,
                                   String aDescricao) {

    public static CreateImpostoCommand from(final String aNome,
                                            final String aTipoCode,
                                            final Integer aAliquota,
                                            final String aDescricao) {

        return new CreateImpostoCommand(
                aNome,
                aTipoCode,
                aAliquota,
                aDescricao);
    }
}
