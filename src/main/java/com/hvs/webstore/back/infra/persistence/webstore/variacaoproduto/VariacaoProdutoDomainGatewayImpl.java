package com.hvs.webstore.back.infra.persistence.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class VariacaoProdutoDomainGatewayImpl implements VariacaoProdutoDomainGateway {

    private final VariacaoProdutoJpaRepository repository;

    public VariacaoProdutoDomainGatewayImpl(VariacaoProdutoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public VariacaoProduto create(VariacaoProduto aVariacaoProduto) {

        return this.repository.save(VariacaoProdutoEntity.from(aVariacaoProduto)).toDomain();
    }

    @Override
    public Optional<VariacaoProduto> read(VariacaoProdutoId aId) {

        return this.repository.findById(aId.getValue()).map(VariacaoProdutoEntity::toDomain);
    }

    @Override
    public Optional<VariacaoProduto> readByUuid(VariacaoProdutoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(VariacaoProdutoEntity::toDomain);
    }

    @Override
    public List<VariacaoProduto> readByProdutoId(Long aProdutoId) {

        return this.repository.findByProduto_Id(aProdutoId).stream().map(VariacaoProdutoEntity::toDomain).toList();
    }

    @Override
    public Pagination<VariacaoProduto> readAll(SearchQuery aQuery) {

        Page<VariacaoProdutoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<VariacaoProdutoEntity> specification =
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
                pages.getContent().stream().map(VariacaoProdutoEntity::toDomain).toList());
    }

    @Override
    public VariacaoProduto update(VariacaoProduto aVariacaoProduto) {

        return this.repository.save(VariacaoProdutoEntity.from(aVariacaoProduto)).toDomain();
    }

    @Override
    public VariacaoProduto patch(VariacaoProduto aVariacaoProduto) {

        return this.repository.save(VariacaoProdutoEntity.from(aVariacaoProduto)).toDomain();
    }

    @Override
    public void delete(VariacaoProduto aVariacaoProduto) {

        final var entity = VariacaoProdutoEntity.from(aVariacaoProduto);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
