package com.hvs.webstore.back.app.command.webstore.cupom;

import java.time.Instant;

public record CreateCupomCommand(String codigo,
                                 String tipoDescontoCode,
                                 Long valorDesconto,
                                 Long valorMinimo,
                                 Integer quantidadeMaxima,
                                 Instant dataExpiracao) {

    public static CreateCupomCommand from(final String codigo,
                                          final String tipoDescontoCode,
                                          final Long valorDesconto,
                                          final Long valorMinimo,
                                          final Integer quantidadeMaxima,
                                          final Instant dataExpiracao) {

        return new CreateCupomCommand(
                codigo,
                tipoDescontoCode,
                valorDesconto,
                valorMinimo,
                quantidadeMaxima,
                dataExpiracao);
    }
}
