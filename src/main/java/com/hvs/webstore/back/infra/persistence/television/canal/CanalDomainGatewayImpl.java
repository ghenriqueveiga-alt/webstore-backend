package com.hvs.webstore.back.infra.persistence.television.canal;

import com.hvs.webstore.back.app.command.television.canal.CanalSearchQuery;
import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.domain.entity.television.canal.CanalDomainGateway;
import com.hvs.webstore.back.domain.entity.television.canal.CanalId;
import com.hvs.webstore.back.domain.entity.television.canal.CanalUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CanalDomainGatewayImpl implements CanalDomainGateway {

    private final CanalJpaRepository repository;

    public CanalDomainGatewayImpl(CanalJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Canal create(Canal aCanal) {

        return this.repository.save(CanalEntity.from(aCanal)).toDomain();
    }

    @Override
    public Optional<Canal> read(CanalId aId) {

        return this.repository.findById(aId.getValue()).map(CanalEntity::toDomain);
    }

    @Override
    public Optional<Canal> readByUuid(CanalUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CanalEntity::toDomain);
    }

    @Override
    public Pagination<Canal> readAll(CanalSearchQuery aQuery) {

        Page<CanalEntity> pages;
        Pageable pageable = PageRequest.of(aQuery.aPage(),
                                           aQuery.aSize(),
                                           Sort.Direction.fromString(aQuery.aDirection()),
                                           aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CanalEntity> specification = (root, query, criteriaBuilder) -> {
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
                pages.getContent().stream().map(CanalEntity::toDomain).toList());
    }

    @Override
    public Canal update(Canal aCanal) {

        return this.repository.save(CanalEntity.from(aCanal)).toDomain();
    }

    @Override
    public Canal patch(Canal aCanal) {

        return this.repository.save(CanalEntity.from(aCanal)).toDomain();
    }

    @Override
    public void delete(Canal aCanal) {

        final var entity = CanalEntity.from(aCanal);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
