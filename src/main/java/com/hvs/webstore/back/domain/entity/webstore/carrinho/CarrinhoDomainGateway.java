package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CarrinhoDomainGateway {

    Carrinho create(Carrinho aCarrinho);

    Optional<Carrinho> read(CarrinhoId aId);

    Optional<Carrinho> readByUuid(CarrinhoUuid aUuid);

    Pagination<Carrinho> readAll(SearchQuery aQuery);

    Optional<Carrinho> readByUsuario(UsuarioId aUsuarioId);

    Carrinho update(Carrinho aCarrinho);

    Carrinho patch(Carrinho aCarrinho);

    void delete(Carrinho aCarrinho);
}
