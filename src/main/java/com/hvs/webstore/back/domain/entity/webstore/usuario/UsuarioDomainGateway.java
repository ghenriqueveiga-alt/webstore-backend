package com.hvs.webstore.back.domain.entity.webstore.usuario;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface UsuarioDomainGateway {

    Usuario create(Usuario aUsuario);

    Optional<Usuario> read(UsuarioId aId);

    Optional<Usuario> readByUuid(UsuarioUuid aUuid);

    Optional<Usuario> readByEmail(String email);

    Pagination<Usuario> readAll(SearchQuery aQuery);

    Usuario update(Usuario aUsuario);

    Usuario patch(Usuario aUsuario);

    void delete(Usuario aUsuario);
}
