package com.hvs.webstore.back.infra.persistence.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteId;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class FreteDomainGatewayImpl implements FreteDomainGateway {

    private final FreteJpaRepository repository;

    public FreteDomainGatewayImpl(FreteJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Frete create(Frete aFrete) {

        return this.repository.save(FreteEntity.from(aFrete)).toDomain();
    }

    @Override
    public Optional<Frete> read(FreteId aId) {

        return this.repository.findById(aId.getValue()).map(FreteEntity::toDomain);
    }

    @Override
    public Optional<Frete> readByUuid(FreteUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(FreteEntity::toDomain);
    }

    @Override
    public Pagination<Frete> readAll(SearchQuery aQuery) {

        Page<FreteEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<FreteEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("tipoFrete")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(FreteEntity::toDomain).toList());
    }

    @Override
    public Frete update(Frete aFrete) {

        return this.repository.save(FreteEntity.from(aFrete)).toDomain();
    }

    @Override
    public Frete patch(Frete aFrete) {

        return this.repository.save(FreteEntity.from(aFrete)).toDomain();
    }

    @Override
    public void delete(Frete aFrete) {

        final var entity = FreteEntity.from(aFrete);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
