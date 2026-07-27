package com.hvs.webstore.back.infra.persistence.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalId;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class PrecoPromocionalDomainGatewayImpl implements PrecoPromocionalDomainGateway {

    private final PrecoPromocionalJpaRepository repository;

    public PrecoPromocionalDomainGatewayImpl(PrecoPromocionalJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public PrecoPromocional create(PrecoPromocional aPrecoPromocional) {

        return this.repository.save(PrecoPromocionalEntity.from(aPrecoPromocional)).toDomain();
    }

    @Override
    public Optional<PrecoPromocional> read(PrecoPromocionalId aId) {

        return this.repository.findById(aId.getValue()).map(PrecoPromocionalEntity::toDomain);
    }

    @Override
    public Optional<PrecoPromocional> readByUuid(PrecoPromocionalUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PrecoPromocionalEntity::toDomain);
    }

    @Override
    public List<PrecoPromocional> readByProdutoId(Long aProdutoId) {

        return this.repository.findByProduto_Id(aProdutoId).stream().map(PrecoPromocionalEntity::toDomain).toList();
    }

    @Override
    public Pagination<PrecoPromocional> readAll(SearchQuery aQuery) {

        Page<PrecoPromocionalEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<PrecoPromocionalEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("precoPromocional")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(PrecoPromocionalEntity::toDomain).toList());
    }

    @Override
    public PrecoPromocional update(PrecoPromocional aPrecoPromocional) {

        return this.repository.save(PrecoPromocionalEntity.from(aPrecoPromocional)).toDomain();
    }

    @Override
    public PrecoPromocional patch(PrecoPromocional aPrecoPromocional) {

        return this.repository.save(PrecoPromocionalEntity.from(aPrecoPromocional)).toDomain();
    }

    @Override
    public void delete(PrecoPromocional aPrecoPromocional) {

        final var entity = PrecoPromocionalEntity.from(aPrecoPromocional);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
