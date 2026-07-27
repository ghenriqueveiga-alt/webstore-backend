package com.hvs.webstore.back.infra.persistence.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoId;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PrecoDomainGatewayImpl implements PrecoDomainGateway {

    private final PrecoJpaRepository repository;

    public PrecoDomainGatewayImpl(PrecoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Preco create(Preco aPreco) {

        return this.repository.save(PrecoEntity.from(aPreco)).toDomain();
    }

    @Override
    public Optional<Preco> read(PrecoId aId) {

        return this.repository.findById(aId.getValue()).map(PrecoEntity::toDomain);
    }

    @Override
    public Optional<Preco> readByUuid(PrecoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PrecoEntity::toDomain);
    }

    @Override
    public Pagination<Preco> readAll(SearchQuery aQuery) {

        Page<PrecoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<PrecoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("tipoPagamento")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(PrecoEntity::toDomain).toList());
    }

    @Override
    public Preco update(Preco aPreco) {

        return this.repository.save(PrecoEntity.from(aPreco)).toDomain();
    }

    @Override
    public Preco patch(Preco aPreco) {

        return this.repository.save(PrecoEntity.from(aPreco)).toDomain();
    }

    @Override
    public void delete(Preco aPreco) {

        final var entity = PrecoEntity.from(aPreco);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
