package com.hvs.webstore.back.app.output.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllTokenVerificacaoOutput(int aCurrentPage,
                                            long aPerPage,
                                            long aTotal,
                                            List<ReadTokenVerificacaoOutput> aTokens) {

    public static ReadAllTokenVerificacaoOutput from(Pagination<TokenVerificacao> aPagination) {

        var list = new ArrayList<ReadTokenVerificacaoOutput>();

        for (var i : aPagination.aContent())
            list.add(ReadTokenVerificacaoOutput.from(i));

        return new ReadAllTokenVerificacaoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }

    public static ReadAllTokenVerificacaoOutput from(List<TokenVerificacao> aTokenVerificacao) {

        var items = new ArrayList<ReadTokenVerificacaoOutput>();

        for (var i : aTokenVerificacao)
            items.add(ReadTokenVerificacaoOutput.from(i));

        return new ReadAllTokenVerificacaoOutput(
                0,
                items.size(),
                1,
                items);
    }
}
