package com.hvs.webstore.back.infra.persistence.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteId;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class CarrinhoFreteDomainGatewayImpl implements CarrinhoFreteDomainGateway {

    private final CarrinhoFreteJpaRepository repository;

    public CarrinhoFreteDomainGatewayImpl(CarrinhoFreteJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public CarrinhoFrete create(CarrinhoFrete aCarrinhoFrete) {

        return this.repository.save(CarrinhoFreteEntity.from(aCarrinhoFrete)).toDomain();
    }

    @Override
    public Optional<CarrinhoFrete> read(CarrinhoFreteId aId) {

        return this.repository.findById(aId.getValue()).map(CarrinhoFreteEntity::toDomain);
    }

    @Override
    public Optional<CarrinhoFrete> readByUuid(CarrinhoFreteUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CarrinhoFreteEntity::toDomain);
    }

    @Override
    public List<CarrinhoFrete> readByCarrinhoId(Long aCarrinhoId) {

        return this.repository.findByCarrinho_Id(aCarrinhoId).stream().map(CarrinhoFreteEntity::toDomain).toList();
    }

    @Override
    public Pagination<CarrinhoFrete> readAll(SearchQuery aQuery) {

        Page<CarrinhoFreteEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CarrinhoFreteEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("transportadora").get("nome")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(CarrinhoFreteEntity::toDomain).toList());
    }

    @Override
    public CarrinhoFrete update(CarrinhoFrete aCarrinhoFrete) {

        return this.repository.save(CarrinhoFreteEntity.from(aCarrinhoFrete)).toDomain();
    }

    @Override
    public CarrinhoFrete patch(CarrinhoFrete aCarrinhoFrete) {

        return this.repository.save(CarrinhoFreteEntity.from(aCarrinhoFrete)).toDomain();
    }

    @Override
    public void delete(CarrinhoFrete aCarrinhoFrete) {

        final var entity = CarrinhoFreteEntity.from(aCarrinhoFrete);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
