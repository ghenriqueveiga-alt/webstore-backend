package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class TokenVerificacaoUuid extends Identifier {

    private final String value;

    private TokenVerificacaoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static TokenVerificacaoUuid unique() {

        return new TokenVerificacaoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static TokenVerificacaoUuid from(final String aId) {

        return new TokenVerificacaoUuid(aId);

    }

    public static TokenVerificacaoUuid from(final UUID aId) {

        return new TokenVerificacaoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        TokenVerificacaoUuid tokenVerificacaoUuid = (TokenVerificacaoUuid) o;

        return Objects.equals(value, tokenVerificacaoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}