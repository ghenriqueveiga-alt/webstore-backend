package com.hvs.webstore.back.infra.persistence.television.grade;

import com.hvs.webstore.back.app.command.television.grade.GradeSearchQuery;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import com.hvs.webstore.back.domain.entity.television.grade.GradeId;
import com.hvs.webstore.back.domain.entity.television.grade.GradeUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class GradeDomainGatewayImpl implements GradeDomainGateway {

    private final GradeJpaRepository repository;

    public GradeDomainGatewayImpl(GradeJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Grade create(Grade aGrade) {

        return this.repository.save(GradeEntity.from(aGrade)).toDomain();
    }

    @Override
    public Optional<Grade> read(GradeId aId) {

        return this.repository.findById(aId.getValue()).map(GradeEntity::toDomain);
    }

    @Override
    public Optional<Grade> readByUuid(GradeUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(GradeEntity::toDomain);
    }

    @Override
    public Pagination<Grade> readAll(GradeSearchQuery aQuery) {

        Page<GradeEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<GradeEntity> specification =
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
                pages.getContent().stream().map(GradeEntity::toDomain).toList());
    }

    @Override
    public Grade update(Grade aGrade) {

        return this.repository.save(GradeEntity.from(aGrade)).toDomain();
    }

    @Override
    public Grade patch(Grade aGrade) {

        return this.repository.save(GradeEntity.from(aGrade)).toDomain();
    }

    @Override
    public void delete(Grade aGrade) {

        final var entity = GradeEntity.from(aGrade);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
