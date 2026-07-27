package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PreferenciaNotificacaoId extends Identifier {

    private final Long value;

    private PreferenciaNotificacaoId(final Long value) {

        this.value = value;
    }

    public static PreferenciaNotificacaoId from(final Long aId) {

        return new PreferenciaNotificacaoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PreferenciaNotificacaoId preferenciaNotificacaoId = (PreferenciaNotificacaoId) o;

        return Objects.equals(value, preferenciaNotificacaoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}