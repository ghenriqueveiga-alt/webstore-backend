package com.hvs.webstore.back.infra.persistence.webstore.transportadora;

import com.hvs.webstore.back.domain.entity.webstore.transportadora.Transportadora;
import com.hvs.webstore.back.domain.entity.webstore.transportadora.TransportadoraDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.transportadora.TransportadoraId;
import com.hvs.webstore.back.domain.entity.webstore.transportadora.TransportadoraUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class TransportadoraDomainGatewayImpl implements TransportadoraDomainGateway {

    private final TransportadoraJpaRepository repository;

    public TransportadoraDomainGatewayImpl(TransportadoraJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Transportadora create(Transportadora aTransportadora) {

        return this.repository.save(TransportadoraEntity.from(aTransportadora)).toDomain();
    }

    @Override
    public Optional<Transportadora> read(TransportadoraId aId) {

        return this.repository.findById(aId.getValue()).map(TransportadoraEntity::toDomain);
    }

    @Override
    public Optional<Transportadora> readByUuid(TransportadoraUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(TransportadoraEntity::toDomain);
    }

    @Override
    public Pagination<Transportadora> readAll(SearchQuery aQuery) {

        Page<TransportadoraEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<TransportadoraEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(TransportadoraEntity::toDomain).toList());
    }

    @Override
    public Transportadora update(Transportadora aTransportadora) {

        return this.repository.save(TransportadoraEntity.from(aTransportadora)).toDomain();
    }

    @Override
    public Transportadora patch(Transportadora aTransportadora) {

        return this.repository.save(TransportadoraEntity.from(aTransportadora)).toDomain();
    }

    @Override
    public void delete(Transportadora aTransportadora) {

        final var entity = TransportadoraEntity.from(aTransportadora);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
