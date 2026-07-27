package com.hvs.webstore.back.domain.entity.webstore.endereco;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface EnderecoDomainGateway {

    Endereco create(Endereco aEndereco);

    Optional<Endereco> read(EnderecoId aId);

    Optional<Endereco> readByUuid(EnderecoUuid aUuid);

    Pagination<Endereco> readAll(SearchQuery aQuery);

    Endereco update(Endereco aEndereco);

    Endereco patch(Endereco aEndereco);

    void delete(Endereco aEndereco);
}
