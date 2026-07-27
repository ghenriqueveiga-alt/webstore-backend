package com.hvs.webstore.back.app.command.webstore.cupom;

import java.time.Instant;

public record UpdateCupomCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String codigo,
                                 String tipoDescontoCode,
                                 Long valorDesconto,
                                 Long valorMinimo,
                                 Integer quantidadeMaxima,
                                 Integer usosAtuais,
                                 Instant dataExpiracao,
                                 Instant criadoEm,
                                 Boolean ativo) {

    public static UpdateCupomCommand from(final Long aId,
                                          final UpdateCupomCommand aInput) {

        return new UpdateCupomCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.codigo,
                aInput.tipoDescontoCode,
                aInput.valorDesconto,
                aInput.valorMinimo,
                aInput.quantidadeMaxima,
                aInput.usosAtuais,
                aInput.dataExpiracao,
                aInput.criadoEm,
                aInput.ativo
        );
    }

    public static UpdateCupomCommand from(final String aUuid,
                                          final UpdateCupomCommand aInput) {

        return new UpdateCupomCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.codigo,
                aInput.tipoDescontoCode,
                aInput.valorDesconto,
                aInput.valorMinimo,
                aInput.quantidadeMaxima,
                aInput.usosAtuais,
                aInput.dataExpiracao,
                aInput.criadoEm,
                aInput.ativo
        );
    }
}
