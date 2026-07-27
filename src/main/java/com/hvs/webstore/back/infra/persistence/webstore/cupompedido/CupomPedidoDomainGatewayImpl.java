package com.hvs.webstore.back.infra.persistence.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoId;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class CupomPedidoDomainGatewayImpl implements CupomPedidoDomainGateway {

    private final CupomPedidoJpaRepository repository;

    public CupomPedidoDomainGatewayImpl(CupomPedidoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public CupomPedido create(CupomPedido aCupomPedido) {

        return this.repository.save(CupomPedidoEntity.from(aCupomPedido)).toDomain();
    }

    @Override
    public Optional<CupomPedido> read(CupomPedidoId aId) {

        return this.repository.findById(aId.getValue()).map(CupomPedidoEntity::toDomain);
    }

    @Override
    public Optional<CupomPedido> readByUuid(CupomPedidoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CupomPedidoEntity::toDomain);
    }

    @Override
    public List<CupomPedido> readByPedidoId(Long aPedidoId) {

        return this.repository.findByPedido_Id(aPedidoId).stream().map(CupomPedidoEntity::toDomain).toList();
    }

    @Override
    public List<CupomPedido> readByCupomId(Long aCupomId) {

        return this.repository.findByCupom_Id(aCupomId).stream().map(CupomPedidoEntity::toDomain).toList();
    }

    @Override
    public Pagination<CupomPedido> readAll(SearchQuery aQuery) {

        Page<CupomPedidoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CupomPedidoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("id").as(String.class)), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(CupomPedidoEntity::toDomain).toList());
    }

    @Override
    public CupomPedido update(CupomPedido aCupomPedido) {

        return this.repository.save(CupomPedidoEntity.from(aCupomPedido)).toDomain();
    }

    @Override
    public CupomPedido patch(CupomPedido aCupomPedido) {

        return this.repository.save(CupomPedidoEntity.from(aCupomPedido)).toDomain();
    }

    @Override
    public void delete(CupomPedido aCupomPedido) {

        final var entity = CupomPedidoEntity.from(aCupomPedido);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
