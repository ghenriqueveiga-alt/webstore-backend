package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class NotificacaoTemplateId extends Identifier {

    private final Long value;

    private NotificacaoTemplateId(final Long value) {

        this.value = value;
    }

    public static NotificacaoTemplateId from(final Long aId) {

        return new NotificacaoTemplateId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        NotificacaoTemplateId notificacaoTemplateId = (NotificacaoTemplateId) o;

        return Objects.equals(value, notificacaoTemplateId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}