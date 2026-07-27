package com.hvs.webstore.back.infra.persistence.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosId;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosUuid;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class ListaDesejosDomainGatewayImpl implements ListaDesejosDomainGateway {

    private final ListaDesejosJpaRepository repository;

    public ListaDesejosDomainGatewayImpl(ListaDesejosJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public ListaDesejos create(ListaDesejos aListaDesejos) {

        return this.repository.save(ListaDesejosEntity.from(aListaDesejos)).toDomain();
    }

    @Override
    public Optional<ListaDesejos> read(ListaDesejosId aId) {

        return this.repository.findById(aId.getValue()).map(ListaDesejosEntity::toDomain);
    }

    @Override
    public Optional<ListaDesejos> readByUuid(ListaDesejosUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(ListaDesejosEntity::toDomain);
    }

    @Override
    public Optional<ListaDesejos> readByUsuario(UsuarioId aUsuarioId) {

        return this.repository.findByUsuarioId(aUsuarioId.getValue()).map(ListaDesejosEntity::toDomain);
    }

    @Override
    public Pagination<ListaDesejos> readAll(SearchQuery aQuery) {

        Page<ListaDesejosEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        pages = this.repository.findAll(pageable);

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(ListaDesejosEntity::toDomain).toList());
    }

    @Override
    public ListaDesejos update(ListaDesejos aListaDesejos) {

        return this.repository.save(ListaDesejosEntity.from(aListaDesejos)).toDomain();
    }

    @Override
    public ListaDesejos patch(ListaDesejos aListaDesejos) {

        return this.repository.save(ListaDesejosEntity.from(aListaDesejos)).toDomain();
    }

    @Override
    public void delete(ListaDesejos aListaDesejos) {

        final var entity = ListaDesejosEntity.from(aListaDesejos);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
