package com.hvs.webstore.back.app.output.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;

public record ReadNotificacaoTemplateOutput(Long aId,
                                            String aUuid,
                                            String aStatusDesc,
                                            String aNome,
                                            String aTipoDesc,
                                            String aAssunto,
                                            String aCorpo,
                                            String aVariaveis) {

    public static ReadNotificacaoTemplateOutput from(NotificacaoTemplate aNotificacaoTemplate) {

        return new ReadNotificacaoTemplateOutput(
                aNotificacaoTemplate.getId().getValue(),
                aNotificacaoTemplate.getUuid().getValue(),
                aNotificacaoTemplate.getStatusCode().getDesc(),
                aNotificacaoTemplate.getNome(),
                aNotificacaoTemplate.getTipo().getDesc(),
                aNotificacaoTemplate.getAssunto(),
                aNotificacaoTemplate.getCorpo(),
                aNotificacaoTemplate.getVariaveis());
    }

    public static ReadNotificacaoTemplateOutput fromSimple(NotificacaoTemplate aNotificacaoTemplate) {

        return new ReadNotificacaoTemplateOutput(
                aNotificacaoTemplate.getId().getValue(),
                aNotificacaoTemplate.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
