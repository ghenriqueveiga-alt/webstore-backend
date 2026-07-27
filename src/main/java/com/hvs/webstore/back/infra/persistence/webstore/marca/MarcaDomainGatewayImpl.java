package com.hvs.webstore.back.infra.persistence.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaId;
import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class MarcaDomainGatewayImpl implements MarcaDomainGateway {

    private final MarcaJpaRepository repository;

    public MarcaDomainGatewayImpl(MarcaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Marca create(Marca aMarca) {

        return this.repository.save(MarcaEntity.from(aMarca)).toDomain();
    }

    @Override
    public Optional<Marca> read(MarcaId aId) {

        return this.repository.findById(aId.getValue()).map(MarcaEntity::toDomain);
    }

    @Override
    public Optional<Marca> readByUuid(MarcaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(MarcaEntity::toDomain);
    }

    @Override
    public Pagination<Marca> readAll(SearchQuery aQuery) {

        Page<MarcaEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<MarcaEntity> specification =
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
                pages.getContent().stream().map(MarcaEntity::toDomain).toList());
    }

    @Override
    public Marca update(Marca aMarca) {

        return this.repository.save(MarcaEntity.from(aMarca)).toDomain();
    }

    @Override
    public Marca patch(Marca aMarca) {

        return this.repository.save(MarcaEntity.from(aMarca)).toDomain();
    }

    @Override
    public void delete(Marca aMarca) {

        final var entity = MarcaEntity.from(aMarca);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
