package com.hvs.webstore.back.app.output.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;

public record DeleteNotificacaoTemplateOutput(Long aId,
                                              String aUuid,
                                              String aMessage) {

    public static DeleteNotificacaoTemplateOutput from(NotificacaoTemplate aNotificacaoTemplate) {

        return new DeleteNotificacaoTemplateOutput(
                aNotificacaoTemplate.getId().getValue(),
                aNotificacaoTemplate.getUuid().getValue(),
                "The NotificacaoTemplate with id: " + aNotificacaoTemplate.getUuid().getValue() + " has been successfully deleted.");
    }
}
