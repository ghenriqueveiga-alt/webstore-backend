package com.hvs.webstore.back.infra.persistence.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomId;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CupomDomainGatewayImpl implements CupomDomainGateway {

    private final CupomJpaRepository repository;

    public CupomDomainGatewayImpl(CupomJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Cupom create(Cupom aCupom) {

        return this.repository.save(CupomEntity.from(aCupom)).toDomain();
    }

    @Override
    public Optional<Cupom> read(CupomId aId) {

        return this.repository.findById(aId.getValue()).map(CupomEntity::toDomain);
    }

    @Override
    public Optional<Cupom> readByUuid(CupomUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CupomEntity::toDomain);
    }

    @Override
    public Optional<Cupom> readByCodigo(String aCodigo) {

        return this.repository.findByCodigo(aCodigo).map(CupomEntity::toDomain);
    }

    @Override
    public Pagination<Cupom> readAll(SearchQuery aQuery) {

        Page<CupomEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CupomEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("codigo")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(CupomEntity::toDomain).toList());
    }

    @Override
    public Cupom update(Cupom aCupom) {

        return this.repository.save(CupomEntity.from(aCupom)).toDomain();
    }

    @Override
    public Cupom patch(Cupom aCupom) {

        return this.repository.save(CupomEntity.from(aCupom)).toDomain();
    }

    @Override
    public void delete(Cupom aCupom) {

        final var entity = CupomEntity.from(aCupom);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
