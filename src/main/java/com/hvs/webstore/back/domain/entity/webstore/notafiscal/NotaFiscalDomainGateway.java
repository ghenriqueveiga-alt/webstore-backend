package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface NotaFiscalDomainGateway {

    NotaFiscal create(NotaFiscal aNotaFiscal);

    Optional<NotaFiscal> read(NotaFiscalId aId);

    Optional<NotaFiscal> readByUuid(NotaFiscalUuid aUuid);

    Optional<NotaFiscal> readByChaveAcesso(String aChaveAcesso);

    Optional<NotaFiscal> readByPedidoId(Long aPedidoId);

    Pagination<NotaFiscal> readAll(SearchQuery aQuery);

    NotaFiscal update(NotaFiscal aNotaFiscal);

    NotaFiscal patch(NotaFiscal aNotaFiscal);

    void delete(NotaFiscal aNotaFiscal);
}
