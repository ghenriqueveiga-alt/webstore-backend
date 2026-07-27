package com.hvs.webstore.back.infra.persistence.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoId;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PedidoDomainGatewayImpl implements PedidoDomainGateway {

    private final PedidoJpaRepository repository;

    public PedidoDomainGatewayImpl(PedidoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Pedido create(Pedido aPedido) {

        return this.repository.save(PedidoEntity.from(aPedido)).toDomain();
    }

    @Override
    public Optional<Pedido> read(PedidoId aId) {

        return this.repository.findById(aId.getValue()).map(PedidoEntity::toDomain);
    }

    @Override
    public Optional<Pedido> readByUuid(PedidoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PedidoEntity::toDomain);
    }

    @Override
    public Pagination<Pedido> readAll(SearchQuery aQuery) {

        Page<PedidoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<PedidoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("usuarioId")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(PedidoEntity::toDomain).toList());
    }

    @Override
    public Pedido update(Pedido aPedido) {

        return this.repository.save(PedidoEntity.from(aPedido)).toDomain();
    }

    @Override
    public Pedido patch(Pedido aPedido) {

        return this.repository.save(PedidoEntity.from(aPedido)).toDomain();
    }

    @Override
    public void delete(Pedido aPedido) {

        final var entity = PedidoEntity.from(aPedido);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
