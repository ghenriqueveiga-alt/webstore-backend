package com.hvs.webstore.back.infra.persistence.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ProdutoDomainGatewayImpl implements ProdutoDomainGateway {

    private final ProdutoJpaRepository repository;

    public ProdutoDomainGatewayImpl(ProdutoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Produto create(Produto aProduto) {

        return this.repository.save(ProdutoEntity.from(aProduto)).toDomain();
    }

    @Override
    public Optional<Produto> read(ProdutoId aId) {

        return this.repository.findById(aId.getValue()).map(ProdutoEntity::toDomain);
    }

    @Override
    public Optional<Produto> readByUuid(ProdutoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(ProdutoEntity::toDomain);
    }

    @Override
    public Pagination<Produto> readAll(SearchQuery aQuery) {

        Page<ProdutoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<ProdutoEntity> specification =
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
                pages.getContent().stream().map(ProdutoEntity::toDomain).toList());
    }

    @Override
    public Produto update(Produto aProduto) {

        return this.repository.save(ProdutoEntity.from(aProduto)).toDomain();
    }

    @Override
    public Produto patch(Produto aProduto) {

        return this.repository.save(ProdutoEntity.from(aProduto)).toDomain();
    }

    @Override
    public void delete(Produto aProduto) {

        final var entity = ProdutoEntity.from(aProduto);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
