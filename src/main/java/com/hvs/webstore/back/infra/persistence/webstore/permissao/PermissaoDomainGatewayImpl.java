package com.hvs.webstore.back.infra.persistence.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoId;
import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PermissaoDomainGatewayImpl implements PermissaoDomainGateway {

    private final PermissaoJpaRepository repository;

    public PermissaoDomainGatewayImpl(PermissaoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Permissao create(Permissao aPermissao) {

        return this.repository.save(PermissaoEntity.from(aPermissao)).toDomain();
    }

    @Override
    public Optional<Permissao> read(PermissaoId aId) {

        return this.repository.findById(aId.getValue()).map(PermissaoEntity::toDomain);
    }

    @Override
    public Optional<Permissao> readByUuid(PermissaoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PermissaoEntity::toDomain);
    }

    @Override
    public Optional<Permissao> readByChave(String aChave) {

        return this.repository.findByChave(aChave).map(PermissaoEntity::toDomain);
    }

    @Override
    public Pagination<Permissao> readAll(SearchQuery aQuery) {

        Page<PermissaoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<PermissaoEntity> specification =
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
                pages.getContent().stream().map(PermissaoEntity::toDomain).toList());
    }

    @Override
    public Permissao update(Permissao aPermissao) {

        return this.repository.save(PermissaoEntity.from(aPermissao)).toDomain();
    }

    @Override
    public Permissao patch(Permissao aPermissao) {

        return this.repository.save(PermissaoEntity.from(aPermissao)).toDomain();
    }

    @Override
    public void delete(Permissao aPermissao) {

        final var entity = PermissaoEntity.from(aPermissao);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
