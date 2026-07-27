package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ListaDesejosDomainGateway {

    ListaDesejos create(ListaDesejos aListaDesejos);

    Optional<ListaDesejos> read(ListaDesejosId aId);

    Optional<ListaDesejos> readByUuid(ListaDesejosUuid aUuid);

    Pagination<ListaDesejos> readAll(SearchQuery aQuery);

    Optional<ListaDesejos> readByUsuario(UsuarioId aUsuarioId);

    ListaDesejos update(ListaDesejos aListaDesejos);

    ListaDesejos patch(ListaDesejos aListaDesejos);

    void delete(ListaDesejos aListaDesejos);
}
