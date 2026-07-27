package com.hvs.webstore.back.app.output.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;

public record UpdateTokenVerificacaoOutput(Long aId,
                                           String aUuid,
                                           String aMessage) {

    public static UpdateTokenVerificacaoOutput from(TokenVerificacao aTokenVerificacao) {

        return new UpdateTokenVerificacaoOutput(
                aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                "TokenVerificacao updated: " + aTokenVerificacao.getUuid().getValue());
    }
}
