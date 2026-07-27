package com.hvs.webstore.back.app.output.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllPreferenciaNotificacaoOutput(int aCurrentPage,
                                                  long aPerPage,
                                                  long aTotal,
                                                  List<ReadPreferenciaNotificacaoOutput> aPreferencias) {

    public static ReadAllPreferenciaNotificacaoOutput from(Pagination<PreferenciaNotificacao> aPagination) {

        var list = new ArrayList<ReadPreferenciaNotificacaoOutput>();

        for (var aPreferenciaNotificacao : aPagination.aContent())
            list.add(ReadPreferenciaNotificacaoOutput.from(aPreferenciaNotificacao));

        return new ReadAllPreferenciaNotificacaoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
