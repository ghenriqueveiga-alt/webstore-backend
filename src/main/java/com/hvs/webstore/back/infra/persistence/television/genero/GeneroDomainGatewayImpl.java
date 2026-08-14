package com.hvs.webstore.back.infra.persistence.television.genero;

import com.hvs.webstore.back.app.command.television.genero.GeneroSearchQuery;
import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroId;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class GeneroDomainGatewayImpl implements GeneroDomainGateway {

    private final GeneroJpaRepository repository;

    public GeneroDomainGatewayImpl(GeneroJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Genero create(Genero aGenero) {

        return this.repository.save(GeneroEntity.from(aGenero)).toDomain();
    }

    @Override
    public Optional<Genero> read(GeneroId aId) {

        return this.repository.findById(aId.getValue()).map(GeneroEntity::toDomain);
    }

    @Override
    public Optional<Genero> readByUuid(GeneroUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(GeneroEntity::toDomain);
    }

    @Override
    public Pagination<Genero> readAll(GeneroSearchQuery aQuery) {

        Page<GeneroEntity> pages;
        Pageable pageable = PageRequest.of(aQuery.aPage(),
                                           aQuery.aSize(),
                                           Sort.Direction.fromString(aQuery.aDirection()),
                                           aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<GeneroEntity> specification = (root, query, criteriaBuilder) -> {
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
                pages.getContent().stream().map(GeneroEntity::toDomain).toList());
    }

    @Override
    public Genero update(Genero aGenero) {

        return this.repository.save(GeneroEntity.from(aGenero)).toDomain();
    }

    @Override
    public Genero patch(Genero aGenero) {

        return this.repository.save(GeneroEntity.from(aGenero)).toDomain();
    }

    @Override
    public void delete(Genero aGenero) {

        final var entity = GeneroEntity.from(aGenero);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
