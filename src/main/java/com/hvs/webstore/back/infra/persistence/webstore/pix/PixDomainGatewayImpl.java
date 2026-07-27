package com.hvs.webstore.back.infra.persistence.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixId;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PixDomainGatewayImpl implements PixDomainGateway {

    private final PixJpaRepository repository;

    public PixDomainGatewayImpl(PixJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Pix create(Pix aPix) {

        return this.repository.save(PixEntity.from(aPix)).toDomain();
    }

    @Override
    public Optional<Pix> read(PixId aId) {

        return this.repository.findById(aId.getValue()).map(PixEntity::toDomain);
    }

    @Override
    public Optional<Pix> readByUuid(PixUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PixEntity::toDomain);
    }

    @Override
    public Pagination<Pix> readAll(SearchQuery aQuery) {

        Page<PixEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<PixEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("chavePix")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(PixEntity::toDomain).toList());
    }

    @Override
    public Pix update(Pix aPix) {

        return this.repository.save(PixEntity.from(aPix)).toDomain();
    }

    @Override
    public Pix patch(Pix aPix) {

        return this.repository.save(PixEntity.from(aPix)).toDomain();
    }

    @Override
    public void delete(Pix aPix) {

        final var entity = PixEntity.from(aPix);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
