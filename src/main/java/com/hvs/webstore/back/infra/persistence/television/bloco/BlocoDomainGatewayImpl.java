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

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<BlocoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("horario")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

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
