package com.hvs.webstore.back.infra.persistence.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoId;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class HistoricoPedidoDomainGatewayImpl implements HistoricoPedidoDomainGateway {

    private final HistoricoPedidoJpaRepository repository;

    public HistoricoPedidoDomainGatewayImpl(HistoricoPedidoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public HistoricoPedido create(HistoricoPedido aHistoricoPedido) {

        return this.repository.save(HistoricoPedidoEntity.from(aHistoricoPedido)).toDomain();
    }

    @Override
    public Optional<HistoricoPedido> read(HistoricoPedidoId aId) {

        return this.repository.findById(aId.getValue()).map(HistoricoPedidoEntity::toDomain);
    }

    @Override
    public Optional<HistoricoPedido> readByUuid(HistoricoPedidoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(HistoricoPedidoEntity::toDomain);
    }

    @Override
    public List<HistoricoPedido> readByPedidoId(Long aPedidoId) {

        return this.repository.findByPedidoIdOrderByDataCriacaoDesc(aPedidoId).stream().map(HistoricoPedidoEntity::toDomain).toList();
    }

    @Override
    public Pagination<HistoricoPedido> readAll(SearchQuery aQuery) {

        Page<HistoricoPedidoEntity> pages;
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
                pages.getContent().stream().map(HistoricoPedidoEntity::toDomain).toList());
    }

    @Override
    public HistoricoPedido update(HistoricoPedido aHistoricoPedido) {

        return this.repository.save(HistoricoPedidoEntity.from(aHistoricoPedido)).toDomain();
    }

    @Override
    public HistoricoPedido patch(HistoricoPedido aHistoricoPedido) {

        return this.repository.save(HistoricoPedidoEntity.from(aHistoricoPedido)).toDomain();
    }

    @Override
    public void delete(HistoricoPedido aHistoricoPedido) {

        final var entity = HistoricoPedidoEntity.from(aHistoricoPedido);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
