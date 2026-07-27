package com.hvs.webstore.back.infra.persistence.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoId;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoUuid;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CarrinhoDomainGatewayImpl implements CarrinhoDomainGateway {

    private final CarrinhoJpaRepository repository;

    public CarrinhoDomainGatewayImpl(CarrinhoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Carrinho create(Carrinho aCarrinho) {

        return this.repository.save(CarrinhoEntity.from(aCarrinho)).toDomain();
    }

    @Override
    public Optional<Carrinho> read(CarrinhoId aId) {

        return this.repository.findById(aId.getValue()).map(CarrinhoEntity::toDomain);
    }

    @Override
    public Optional<Carrinho> readByUuid(CarrinhoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CarrinhoEntity::toDomain);
    }

    @Override
    public Optional<Carrinho> readByUsuario(UsuarioId aUsuarioId) {

        return this.repository.findByUsuario_Id(aUsuarioId.getValue()).map(CarrinhoEntity::toDomain);
    }

    @Override
    public Pagination<Carrinho> readAll(SearchQuery aQuery) {

        Page<CarrinhoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CarrinhoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("statusCode")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(CarrinhoEntity::toDomain).toList());
    }

    @Override
    public Carrinho update(Carrinho aCarrinho) {

        return this.repository.save(CarrinhoEntity.from(aCarrinho)).toDomain();
    }

    @Override
    public Carrinho patch(Carrinho aCarrinho) {

        return this.repository.save(CarrinhoEntity.from(aCarrinho)).toDomain();
    }

    @Override
    public void delete(Carrinho aCarrinho) {

        final var entity = CarrinhoEntity.from(aCarrinho);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
