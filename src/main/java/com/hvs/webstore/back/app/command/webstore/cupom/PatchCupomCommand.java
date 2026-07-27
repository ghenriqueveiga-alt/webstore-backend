package com.hvs.webstore.back.app.command.webstore.cupom;

import java.time.Instant;

public record PatchCupomCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                String codigo,
                                String tipoDescontoCode,
                                Long valorDesconto,
                                Long valorMinimo,
                                Integer quantidadeMaxima,
                                Integer usosAtuais,
                                Instant dataExpiracao,
                                Boolean ativo) {

    public static PatchCupomCommand from(final Long aId,
                                         final PatchCupomCommand aInput) {

        return new PatchCupomCommand(
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
                aInput.ativo);
    }

    public static PatchCupomCommand from(final String aUuid,
                                         final PatchCupomCommand aInput) {

        return new PatchCupomCommand(
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
                aInput.ativo);
    }
}
