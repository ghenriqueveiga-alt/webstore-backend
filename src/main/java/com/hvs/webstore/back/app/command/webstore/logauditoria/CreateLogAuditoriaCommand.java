package com.hvs.webstore.back.app.command.webstore.logauditoria;

public record CreateLogAuditoriaCommand(String aEntidadeNome,
                                        Long aEntidadeId,
                                        String aAcaoDesc,
                                        Long aValorAntigoId,
                                        Long aValorNovoId,
                                        Long aUsuarioId) {

    public static CreateLogAuditoriaCommand from(final String aEntidadeNome,
                                                 final Long aEntidadeId,
                                                 final String aAcaoDesc,
                                                 final Long aValorAntigoId,
                                                 final Long aValorNovoId,
                                                 final Long aUsuarioId) {

        return new CreateLogAuditoriaCommand(
                aEntidadeNome,
                aEntidadeId,
                aAcaoDesc,
                aValorAntigoId,
                aValorNovoId,
                aUsuarioId);
    }
}
