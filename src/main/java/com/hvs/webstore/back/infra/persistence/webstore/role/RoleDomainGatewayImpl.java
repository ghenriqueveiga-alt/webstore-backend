package com.hvs.webstore.back.infra.persistence.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleId;
import com.hvs.webstore.back.domain.entity.webstore.role.RoleUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class RoleDomainGatewayImpl implements RoleDomainGateway {

    private final RoleJpaRepository repository;

    public RoleDomainGatewayImpl(RoleJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Role create(Role aRole) {

        return this.repository.save(RoleEntity.from(aRole)).toDomain();
    }

    @Override
    public Optional<Role> read(RoleId aId) {

        return this.repository.findById(aId.getValue()).map(RoleEntity::toDomain);
    }

    @Override
    public Optional<Role> readByUuid(RoleUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(RoleEntity::toDomain);
    }

    @Override
    public Optional<Role> readByNome(String aNome) {

        return this.repository.findByNome(aNome).map(RoleEntity::toDomain);
    }

    @Override
    public Pagination<Role> readAll(SearchQuery aQuery) {

        Page<RoleEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<RoleEntity> specification =
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
                pages.getContent().stream().map(RoleEntity::toDomain).toList());
    }

    @Override
    public Role update(Role aRole) {

        return this.repository.save(RoleEntity.from(aRole)).toDomain();
    }

    @Override
    public Role patch(Role aRole) {

        return this.repository.save(RoleEntity.from(aRole)).toDomain();
    }

    @Override
    public void delete(Role aRole) {

        final var entity = RoleEntity.from(aRole);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
