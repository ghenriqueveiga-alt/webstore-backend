package com.hvs.webstore.back.app.output.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;

public record PatchNotificacaoTemplateOutput(Long aId,
                                             String aUuid,
                                             String aMessage) {

    public static PatchNotificacaoTemplateOutput from(NotificacaoTemplate aNotificacaoTemplate) {

        return new PatchNotificacaoTemplateOutput(
                aNotificacaoTemplate.getId().getValue(),
                aNotificacaoTemplate.getUuid().getValue(),
                "The NotificacaoTemplate with id: " + aNotificacaoTemplate.getUuid().getValue() + " has been successfully patched.");
    }
}
