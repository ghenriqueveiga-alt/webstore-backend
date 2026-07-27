package com.hvs.webstore.back.app.output.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;

public record DeleteTokenVerificacaoOutput(Long aId,
                                           String aUuid,
                                           String aMessage) {

    public static DeleteTokenVerificacaoOutput from(TokenVerificacao aTokenVerificacao) {

        return new DeleteTokenVerificacaoOutput(
                aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                "TokenVerificacao deleted: " + aTokenVerificacao.getUuid().getValue());
    }
}
