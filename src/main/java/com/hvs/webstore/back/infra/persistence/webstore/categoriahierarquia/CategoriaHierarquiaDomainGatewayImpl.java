package com.hvs.webstore.back.infra.persistence.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaId;
import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class CategoriaHierarquiaDomainGatewayImpl implements CategoriaHierarquiaDomainGateway {

    private final CategoriaHierarquiaJpaRepository repository;

    public CategoriaHierarquiaDomainGatewayImpl(CategoriaHierarquiaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public CategoriaHierarquia create(CategoriaHierarquia aCategoriaHierarquia) {

        return this.repository.save(CategoriaHierarquiaEntity.from(aCategoriaHierarquia)).toDomain();
    }

    @Override
    public Optional<CategoriaHierarquia> read(CategoriaHierarquiaId aId) {

        return this.repository.findById(aId.getValue()).map(CategoriaHierarquiaEntity::toDomain);
    }

    @Override
    public Optional<CategoriaHierarquia> readByUuid(CategoriaHierarquiaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CategoriaHierarquiaEntity::toDomain);
    }

    @Override
    public List<CategoriaHierarquia> readByCategoriaId(Long aCategoriaId) {

        return this.repository.findByCategoria_Id(aCategoriaId).stream().map(CategoriaHierarquiaEntity::toDomain).toList();
    }

    @Override
    public List<CategoriaHierarquia> readByCategoriaPaiId(Long aCategoriaPaiId) {

        return this.repository.findByCategoriaPai_Id(aCategoriaPaiId).stream().map(CategoriaHierarquiaEntity::toDomain).toList();
    }

    @Override
    public Pagination<CategoriaHierarquia> readAll(SearchQuery aQuery) {

        Page<CategoriaHierarquiaEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CategoriaHierarquiaEntity> specification =
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
                pages.getContent().stream().map(CategoriaHierarquiaEntity::toDomain).toList());
    }

    @Override
    public Pagination<CategoriaHierarquia> readRoots(SearchQuery aQuery) {

        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        Specification<CategoriaHierarquiaEntity> spec = (root, query, cb) -> cb.isNull(root.get("categoriaPai"));

        var page = this.repository.findAll(spec, pageable);

        return new Pagination<>(
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent().stream().map(CategoriaHierarquiaEntity::toDomain).toList());
    }

    @Override
    public CategoriaHierarquia update(CategoriaHierarquia aCategoriaHierarquia) {

        return this.repository.save(CategoriaHierarquiaEntity.from(aCategoriaHierarquia)).toDomain();
    }

    @Override
    public CategoriaHierarquia patch(CategoriaHierarquia aCategoriaHierarquia) {

        return this.repository.save(CategoriaHierarquiaEntity.from(aCategoriaHierarquia)).toDomain();
    }

    @Override
    public void delete(CategoriaHierarquia aCategoriaHierarquia) {

        final var entity = CategoriaHierarquiaEntity.from(aCategoriaHierarquia);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
