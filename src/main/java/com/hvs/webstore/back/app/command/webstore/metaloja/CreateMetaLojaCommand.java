package com.hvs.webstore.back.app.command.webstore.metaloja;

public record CreateMetaLojaCommand(String aChave,
                                    String aValor,
                                    String aDescricao,
                                    String aTipoCode) {

    public static CreateMetaLojaCommand from(final String aChave,
                                             final String aValor,
                                             final String aDescricao,
                                             final String aTipoCode) {

        return new CreateMetaLojaCommand(
                aChave,
                aValor,
                aDescricao,
                aTipoCode);
    }
}
