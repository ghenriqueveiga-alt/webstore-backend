package com.hvs.webstore.back.infra.persistence.television.programa;

import com.hvs.webstore.back.app.command.television.programa.ProgramaSearchQuery;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaId;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ProgramaDomainGatewayImpl implements ProgramaDomainGateway {

    private final ProgramaJpaRepository repository;

    public ProgramaDomainGatewayImpl(ProgramaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Programa create(Programa aPrograma) {

        return this.repository.save(ProgramaEntity.from(aPrograma)).toDomain();
    }

    @Override
    public Optional<Programa> read(ProgramaId aId) {

        return this.repository.findById(aId.getValue()).map(ProgramaEntity::toDomain);
    }

    @Override
    public Optional<Programa> readByUuid(ProgramaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(ProgramaEntity::toDomain);
    }

    @Override
    public Pagination<Programa> readAll(ProgramaSearchQuery aQuery) {

        Page<ProgramaEntity> pages;
        Pageable pageable = PageRequest.of(aQuery.aPage(),
                                           aQuery.aSize(),
                                           Sort.Direction.fromString(aQuery.aDirection()),
                                           aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<ProgramaEntity> specification = (root, query, criteriaBuilder) -> {
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
                pages.getContent().stream().map(ProgramaEntity::toDomain).toList());
    }

    @Override
    public Programa update(Programa aPrograma) {

        return this.repository.save(ProgramaEntity.from(aPrograma)).toDomain();
    }

    @Override
    public Programa patch(Programa aPrograma) {

        return this.repository.save(ProgramaEntity.from(aPrograma)).toDomain();
    }

    @Override
    public void delete(Programa aPrograma) {

        final var entity = ProgramaEntity.from(aPrograma);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
