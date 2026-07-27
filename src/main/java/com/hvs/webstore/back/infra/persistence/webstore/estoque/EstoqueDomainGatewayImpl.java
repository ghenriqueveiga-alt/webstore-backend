package com.hvs.webstore.back.infra.persistence.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueId;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueUuid;
import com.hvs.webstore.back.domain.entity.webstore.estoque.MovimentoEstoque;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class EstoqueDomainGatewayImpl implements EstoqueDomainGateway {

    private final EstoqueJpaRepository repository;
    private final MovimentoEstoqueJpaRepository movimentoRepository;

    public EstoqueDomainGatewayImpl(EstoqueJpaRepository repository,
                                    MovimentoEstoqueJpaRepository movimentoRepository) {

        this.repository = repository;
        this.movimentoRepository = movimentoRepository;
    }

    @Override
    public Estoque create(Estoque aEstoque) {

        return this.repository.save(EstoqueEntity.from(aEstoque)).toDomain();
    }

    @Override
    public Optional<Estoque> read(EstoqueId aId) {

        return this.repository.findById(aId.getValue()).map(EstoqueEntity::toDomain);
    }

    @Override
    public Optional<Estoque> readByUuid(EstoqueUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(EstoqueEntity::toDomain);
    }

    @Override
    public Optional<Estoque> findByProduto(Long aProdutoId) {

        return this.repository.findByProdutoId(aProdutoId).map(EstoqueEntity::toDomain);
    }

    @Override
    public Pagination<Estoque> readAll(SearchQuery aQuery) {

        Page<EstoqueEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<EstoqueEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("uuid")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(EstoqueEntity::toDomain).toList());
    }

    @Override
    public Estoque update(Estoque aEstoque) {

        return this.repository.save(EstoqueEntity.from(aEstoque)).toDomain();
    }

    @Override
    public Estoque patch(Estoque aEstoque) {

        return this.repository.save(EstoqueEntity.from(aEstoque)).toDomain();
    }

    @Override
    public void delete(Estoque aEstoque) {

        final var entity = EstoqueEntity.from(aEstoque);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }

    @Override
    public MovimentoEstoque createMovimento(MovimentoEstoque aMovimento) {

        return this.movimentoRepository.save(MovimentoEstoqueEntity.from(aMovimento)).toDomain();
    }
}
