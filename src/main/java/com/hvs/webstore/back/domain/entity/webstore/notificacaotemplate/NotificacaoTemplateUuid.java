package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class NotificacaoTemplateUuid extends Identifier {

    private final String value;

    private NotificacaoTemplateUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static NotificacaoTemplateUuid unique() {

        return new NotificacaoTemplateUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static NotificacaoTemplateUuid from(final String aId) {

        return new NotificacaoTemplateUuid(aId);

    }

    public static NotificacaoTemplateUuid from(final UUID aId) {

        return new NotificacaoTemplateUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        NotificacaoTemplateUuid notificacaoTemplateUuid = (NotificacaoTemplateUuid) o;

        return Objects.equals(value, notificacaoTemplateUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}