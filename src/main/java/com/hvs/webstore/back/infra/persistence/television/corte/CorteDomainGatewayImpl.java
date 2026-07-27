package com.hvs.webstore.back.infra.persistence.television.corte;

import com.hvs.webstore.back.app.command.television.corte.CorteSearchQuery;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.domain.entity.television.corte.CorteId;
import com.hvs.webstore.back.domain.entity.television.corte.CorteUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CorteDomainGatewayImpl implements CorteDomainGateway {

    private final CorteJpaRepository repository;

    public CorteDomainGatewayImpl(CorteJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Corte create(Corte aCorte) {

        return this.repository.save(CorteEntity.from(aCorte)).toDomain();
    }

    @Override
    public Optional<Corte> read(CorteId aId) {

        return this.repository.findById(aId.getValue()).map(CorteEntity::toDomain);
    }

    @Override
    public Optional<Corte> readByUuid(CorteUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CorteEntity::toDomain);
    }

    @Override
    public Pagination<Corte> readAll(CorteSearchQuery aQuery) {

        Page<CorteEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CorteEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("tipoCode")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(CorteEntity::toDomain).toList());
    }

    @Override
    public Corte update(Corte aCorte) {

        return this.repository.save(CorteEntity.from(aCorte)).toDomain();
    }

    @Override
    public Corte patch(Corte aCorte) {

        return this.repository.save(CorteEntity.from(aCorte)).toDomain();
    }

    @Override
    public void delete(Corte aCorte) {

        final var entity = CorteEntity.from(aCorte);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
