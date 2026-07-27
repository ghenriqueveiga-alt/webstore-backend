package com.hvs.webstore.back.app.output.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;

public record CupomOutput(Long id,
                          String uuid,
                          String status,
                          String codigo,
                          String tipoDesconto,
                          Long valorDesconto,
                          Long valorMinimo,
                          Integer quantidadeMaxima,
                          Integer usosAtuais,
                          String dataExpiracao,
                          String criadoEm,
                          Boolean ativo) {

    public static CupomOutput from(Cupom aCupom) {

        return new CupomOutput(
                aCupom.getId().getValue(),
                aCupom.getUuid().getValue(),
                aCupom.getStatusCode().getDesc(),
                aCupom.getCodigo(),
                aCupom.getTipoDesconto().getDesc(),
                aCupom.getValorDesconto(),
                aCupom.getValorMinimo(),
                aCupom.getQuantidadeMaxima(),
                aCupom.getUsosAtuais(),
                aCupom.getDataExpiracao() != null ? aCupom.getDataExpiracao().toString() : null,
                aCupom.getCriadoEm() != null ? aCupom.getCriadoEm().toString() : null,
                aCupom.getAtivo());
    }

    public static CupomOutput fromSimple(Cupom aCupom) {

        return new CupomOutput(
                aCupom.getId().getValue(),
                aCupom.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
