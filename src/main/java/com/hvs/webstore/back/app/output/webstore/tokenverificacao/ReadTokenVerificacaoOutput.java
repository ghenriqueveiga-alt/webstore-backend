package com.hvs.webstore.back.app.output.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import java.time.Instant;

public record ReadTokenVerificacaoOutput(Long aId,
                                         String aUuid,
                                         String aStatusDesc,
                                         Long aUsuarioId,
                                         String aToken,
                                         String aTipoCode,
                                         Instant aExpiradoEm,
                                         Instant aUtilizadoEm,
                                         Instant aCriadoEm) {

    public static ReadTokenVerificacaoOutput from(TokenVerificacao aTokenVerificacao) {

        return new ReadTokenVerificacaoOutput(
                aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                aTokenVerificacao.getStatusCode().getDesc(),
                aTokenVerificacao.getUsuario() != null ? aTokenVerificacao.getUsuario().getId().getValue() : null,
                aTokenVerificacao.getToken(),
                aTokenVerificacao.getTipoToken().getCode(),
                aTokenVerificacao.getExpiradoEm(),
                aTokenVerificacao.getUtilizadoEm(),
                aTokenVerificacao.getCriadoEm());
    }

    public static ReadTokenVerificacaoOutput fromSimple(TokenVerificacao aTokenVerificacao) {

        return new ReadTokenVerificacaoOutput(
                aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
