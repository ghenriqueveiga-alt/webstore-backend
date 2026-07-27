package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class TokenVerificacaoId extends Identifier {

    private final Long value;

    private TokenVerificacaoId(final Long value) {

        this.value = value;
    }

    public static TokenVerificacaoId from(final Long aId) {

        return new TokenVerificacaoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        TokenVerificacaoId tokenVerificacaoId = (TokenVerificacaoId) o;

        return Objects.equals(value, tokenVerificacaoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}