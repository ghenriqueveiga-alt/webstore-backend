package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface FormaPagamentoDomainGateway {

    FormaPagamento create(FormaPagamento aFormaPagamento);

    Optional<FormaPagamento> read(FormaPagamentoId aId);

    Optional<FormaPagamento> readByUuid(FormaPagamentoUuid aUuid);

    Pagination<FormaPagamento> readAll(SearchQuery aQuery);

    FormaPagamento update(FormaPagamento aFormaPagamento);

    FormaPagamento patch(FormaPagamento aFormaPagamento);

    void delete(FormaPagamento aFormaPagamento);
}
