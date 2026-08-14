package com.hvs.webstore.back.app.output.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllNotificacaoTemplateOutput(int aCurrentPage,
                                               long aPerPage,
                                               long aTotal,
                                               List<ReadNotificacaoTemplateOutput> aNotificacoes) {

    public static ReadAllNotificacaoTemplateOutput from(Pagination<NotificacaoTemplate> aPagination) {

        final List<ReadNotificacaoTemplateOutput> list = new ArrayList<>();

        for (NotificacaoTemplate item : aPagination.aContent())
            list.add(ReadNotificacaoTemplateOutput.from(item));

        return new ReadAllNotificacaoTemplateOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
