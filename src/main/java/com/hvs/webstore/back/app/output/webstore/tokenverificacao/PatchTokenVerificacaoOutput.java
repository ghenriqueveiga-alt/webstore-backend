package com.hvs.webstore.back.app.output.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;

public record PatchTokenVerificacaoOutput(Long aId,
                                          String aUuid,
                                          String aMessage) {

    public static PatchTokenVerificacaoOutput from(TokenVerificacao aTokenVerificacao) {

        return new PatchTokenVerificacaoOutput(
                aTokenVerificacao.getId().getValue(),
                aTokenVerificacao.getUuid().getValue(),
                "TokenVerificacao patched: " + aTokenVerificacao.getUuid().getValue());
    }
}
