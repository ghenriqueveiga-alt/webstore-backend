package com.hvs.webstore.back.infra.persistence.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoId;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ImpostoDomainGatewayImpl implements ImpostoDomainGateway {

    private final ImpostoJpaRepository repository;

    public ImpostoDomainGatewayImpl(ImpostoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Imposto create(Imposto aImposto) {

        return this.repository.save(ImpostoEntity.from(aImposto)).toDomain();
    }

    @Override
    public Optional<Imposto> read(ImpostoId aId) {

        return this.repository.findById(aId.getValue()).map(ImpostoEntity::toDomain);
    }

    @Override
    public Optional<Imposto> readByUuid(ImpostoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(ImpostoEntity::toDomain);
    }

    @Override
    public Pagination<Imposto> readAll(SearchQuery aQuery) {

        Page<ImpostoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<ImpostoEntity> specification =
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
                pages.getContent().stream().map(ImpostoEntity::toDomain).toList());
    }

    @Override
    public Imposto update(Imposto aImposto) {

        return this.repository.save(ImpostoEntity.from(aImposto)).toDomain();
    }

    @Override
    public Imposto patch(Imposto aImposto) {

        return this.repository.save(ImpostoEntity.from(aImposto)).toDomain();
    }

    @Override
    public void delete(Imposto aImposto) {

        final var entity = ImpostoEntity.from(aImposto);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
