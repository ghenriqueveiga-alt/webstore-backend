package com.hvs.webstore.back.app.output.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import java.util.List;

public record ReadByTipoNotificacaoTemplateOutput(List<ReadNotificacaoTemplateOutput> aNotificacoes) {

    public static ReadByTipoNotificacaoTemplateOutput from(List<NotificacaoTemplate> aList) {

        return new ReadByTipoNotificacaoTemplateOutput(aList.stream().map(ReadNotificacaoTemplateOutput::from).toList());
    }
}
