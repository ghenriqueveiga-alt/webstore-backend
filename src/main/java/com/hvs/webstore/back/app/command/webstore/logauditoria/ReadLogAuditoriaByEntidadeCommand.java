package com.hvs.webstore.back.app.command.webstore.logauditoria;

public record ReadLogAuditoriaByEntidadeCommand(String aEntidadeNome,
                                                Long aEntidadeId) {

    public static ReadLogAuditoriaByEntidadeCommand from(final String aEntidadeNome,
                                                         final Long aEntidadeId) {

        return new ReadLogAuditoriaByEntidadeCommand(
                aEntidadeNome,
                aEntidadeId
        );
    }
}
