package com.hvs.webstore.back.infra.persistence.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoId;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class AvaliacaoDomainGatewayImpl implements AvaliacaoDomainGateway {

    private final AvaliacaoJpaRepository repository;

    public AvaliacaoDomainGatewayImpl(AvaliacaoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Avaliacao create(Avaliacao aAvaliacao) {

        return this.repository.save(AvaliacaoEntity.from(aAvaliacao)).toDomain();
    }

    @Override
    public Optional<Avaliacao> read(AvaliacaoId aId) {

        return this.repository.findById(aId.getValue()).map(AvaliacaoEntity::toDomain);
    }

    @Override
    public Optional<Avaliacao> readByUuid(AvaliacaoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(AvaliacaoEntity::toDomain);
    }

    @Override
    public Pagination<Avaliacao> readByProduto(Long aProdutoId, SearchQuery aQuery) {

        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        var pages = this.repository.findByProdutoId(aProdutoId, pageable);

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(AvaliacaoEntity::toDomain).toList());
    }

    @Override
    public Pagination<Avaliacao> readAll(SearchQuery aQuery) {

        Page<AvaliacaoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<AvaliacaoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(AvaliacaoEntity::toDomain).toList());
    }

    @Override
    public Avaliacao update(Avaliacao aAvaliacao) {

        return this.repository.save(AvaliacaoEntity.from(aAvaliacao)).toDomain();
    }

    @Override
    public Avaliacao patch(Avaliacao aAvaliacao) {

        return this.repository.save(AvaliacaoEntity.from(aAvaliacao)).toDomain();
    }

    @Override
    public void delete(Avaliacao aAvaliacao) {

        final var entity = AvaliacaoEntity.from(aAvaliacao);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
