package com.hvs.webstore.back.infra.persistence.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaId;
import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CategoriaDomainGatewayImpl implements CategoriaDomainGateway {

    private final CategoriaJpaRepository repository;

    public CategoriaDomainGatewayImpl(CategoriaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Categoria create(Categoria aCategoria) {

        return this.repository.save(CategoriaEntity.from(aCategoria)).toDomain();
    }

    @Override
    public Optional<Categoria> read(CategoriaId aId) {

        return this.repository.findById(aId.getValue()).map(CategoriaEntity::toDomain);
    }

    @Override
    public Optional<Categoria> readByUuid(CategoriaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CategoriaEntity::toDomain);
    }

    @Override
    public Pagination<Categoria> readAll(SearchQuery aQuery) {

        Page<CategoriaEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CategoriaEntity> specification =
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
                pages.getContent().stream().map(CategoriaEntity::toDomain).toList());
    }

    @Override
    public Categoria update(Categoria aCategoria) {

        return this.repository.save(CategoriaEntity.from(aCategoria)).toDomain();
    }

    @Override
    public Categoria patch(Categoria aCategoria) {

        return this.repository.save(CategoriaEntity.from(aCategoria)).toDomain();
    }

    @Override
    public void delete(Categoria aCategoria) {

        final var entity = CategoriaEntity.from(aCategoria);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
