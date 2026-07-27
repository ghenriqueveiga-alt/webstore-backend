package com.hvs.webstore.back.app.output.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;

public record CreateTokenVerificacaoOutput(Long aId,
                                           String aUuid,
                                           String aMessage) {

    public static CreateTokenVerificacaoOutput from(TokenVerificacao aTokenVerificacao) {

        return new CreateTokenVerificacaoOutput(
                aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                "TokenVerificacao created: " + aTokenVerificacao.getUuid().getValue());
    }
}
