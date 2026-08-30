package com.hvs.webstore.back.infra.persistence.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.BlocoSearchQuery;
import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoId;
import com.hvs.webstore.back.domain.entity.television.bloco.BlocoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class BlocoDomainGatewayImpl implements BlocoDomainGateway {

    private final BlocoJpaRepository repository;

    public BlocoDomainGatewayImpl(BlocoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Bloco create(Bloco aBloco) {

        return this.repository.save(BlocoEntity.from(aBloco)).toDomain();
    }

    @Override
    public Optional<Bloco> read(BlocoId aId) {

        return this.repository.findById(aId.getValue()).map(BlocoEntity::toDomain);
    }

    @Override
    public Optional<Bloco> readByUuid(BlocoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(BlocoEntity::toDomain);
    }

    @Override
    public Pagination<Bloco> readAll(BlocoSearchQuery aQuery) {

        Page<BlocoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        Specification<BlocoEntity> specification = (root, query, criteriaBuilder) -> {
            var predicates = new java.util.ArrayList<jakarta.persistence.criteria.Predicate>();

            predicates.add(criteriaBuilder.equal(root.get("statusDesc"), "Active"));

            if (aQuery.aGradeId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("grade").get("id"), aQuery.aGradeId()));
            }

            if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
                String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("horario")), likePattern));
            }

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };

        pages = this.repository.findAll(specification, pageable);

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(BlocoEntity::toDomain).toList());
    }

    @Override
    public Bloco update(Bloco aBloco) {

        return this.repository.save(BlocoEntity.from(aBloco)).toDomain();
    }

    @Override
    public Bloco patch(Bloco aBloco) {

        return this.repository.save(BlocoEntity.from(aBloco)).toDomain();
    }

    @Override
    public void delete(Bloco aBloco) {

        final var entity = BlocoEntity.from(aBloco);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
