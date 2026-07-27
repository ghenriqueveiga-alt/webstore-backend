package com.hvs.webstore.back.infra.persistence.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoId;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CartaoDomainGatewayImpl implements CartaoDomainGateway {

    private final CartaoJpaRepository repository;

    public CartaoDomainGatewayImpl(CartaoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Cartao create(Cartao aCartao) {

        return this.repository.save(CartaoEntity.from(aCartao)).toDomain();
    }

    @Override
    public Optional<Cartao> read(CartaoId aId) {

        return this.repository.findById(aId.getValue()).map(CartaoEntity::toDomain);
    }

    @Override
    public Optional<Cartao> readByUuid(CartaoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CartaoEntity::toDomain);
    }

    @Override
    public Pagination<Cartao> readAll(SearchQuery aQuery) {

        Page<CartaoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CartaoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeTitular")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(CartaoEntity::toDomain).toList());
    }

    @Override
    public Cartao update(Cartao aCartao) {

        return this.repository.save(CartaoEntity.from(aCartao)).toDomain();
    }

    @Override
    public Cartao patch(Cartao aCartao) {

        return this.repository.save(CartaoEntity.from(aCartao)).toDomain();
    }

    @Override
    public void delete(Cartao aCartao) {

        final var entity = CartaoEntity.from(aCartao);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
