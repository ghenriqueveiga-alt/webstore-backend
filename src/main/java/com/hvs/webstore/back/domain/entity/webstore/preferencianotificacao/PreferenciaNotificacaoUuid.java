package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PreferenciaNotificacaoUuid extends Identifier {

    private final String value;

    private PreferenciaNotificacaoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PreferenciaNotificacaoUuid unique() {

        return new PreferenciaNotificacaoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PreferenciaNotificacaoUuid from(final String aId) {

        return new PreferenciaNotificacaoUuid(aId);

    }

    public static PreferenciaNotificacaoUuid from(final UUID aId) {

        return new PreferenciaNotificacaoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PreferenciaNotificacaoUuid preferenciaNotificacaoUuid = (PreferenciaNotificacaoUuid) o;

        return Objects.equals(value, preferenciaNotificacaoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}