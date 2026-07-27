package com.hvs.webstore.back.app.output.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;

public record UpdateNotificacaoTemplateOutput(Long aId,
                                              String aUuid,
                                              String aMessage) {

    public static UpdateNotificacaoTemplateOutput from(NotificacaoTemplate aNotificacaoTemplate) {

        return new UpdateNotificacaoTemplateOutput(
                aNotificacaoTemplate.getId().getValue(),
                aNotificacaoTemplate.getUuid().getValue(),
                "The NotificacaoTemplate with id: " + aNotificacaoTemplate.getUuid().getValue() + " has been successfully updated.");
    }
}
