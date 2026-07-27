package com.hvs.webstore.back.infra.persistence.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.ArquivoSearchQuery;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ArquivoDomainGatewayImpl implements ArquivoDomainGateway {

    private final ArquivoJpaRepository repository;

    public ArquivoDomainGatewayImpl(ArquivoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Arquivo create(Arquivo aArquivo) {

        return this.repository.save(ArquivoEntity.from(aArquivo)).toDomain();
    }

    @Override
    public Optional<Arquivo> read(ArquivoId aId) {

        return this.repository.findById(aId.getValue()).map(ArquivoEntity::toDomain);
    }

    @Override
    public Optional<Arquivo> readByUuid(ArquivoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(ArquivoEntity::toDomain);
    }

    @Override
    public Pagination<Arquivo> readAll(ArquivoSearchQuery aQuery) {

        Page<ArquivoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<ArquivoEntity> specification =
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
                pages.getContent().stream().map(ArquivoEntity::toDomain).toList());
    }

    @Override
    public Arquivo update(Arquivo aArquivo) {

        return this.repository.save(ArquivoEntity.from(aArquivo)).toDomain();
    }

    @Override
    public Arquivo patch(Arquivo aArquivo) {

        return this.repository.save(ArquivoEntity.from(aArquivo)).toDomain();
    }

    @Override
    public void delete(Arquivo aArquivo) {

        final var entity = ArquivoEntity.from(aArquivo);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
