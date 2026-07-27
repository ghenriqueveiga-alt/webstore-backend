package com.hvs.webstore.back.infra.persistence.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaId;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CaracteristicaDomainGatewayImpl implements CaracteristicaDomainGateway {

    private final CaracteristicaJpaRepository repository;

    public CaracteristicaDomainGatewayImpl(CaracteristicaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Caracteristica create(Caracteristica aCaracteristica) {

        return this.repository.save(CaracteristicaEntity.from(aCaracteristica)).toDomain();
    }

    @Override
    public Optional<Caracteristica> read(CaracteristicaId aId) {

        return this.repository.findById(aId.getValue()).map(CaracteristicaEntity::toDomain);
    }

    @Override
    public Optional<Caracteristica> readByUuid(CaracteristicaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(CaracteristicaEntity::toDomain);
    }

    @Override
    public Pagination<Caracteristica> readAll(SearchQuery aQuery) {

        Page<CaracteristicaEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<CaracteristicaEntity> specification =
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
                pages.getContent().stream().map(CaracteristicaEntity::toDomain).toList());
    }

    @Override
    public Caracteristica update(Caracteristica aCaracteristica) {

        return this.repository.save(CaracteristicaEntity.from(aCaracteristica)).toDomain();
    }

    @Override
    public Caracteristica patch(Caracteristica aCaracteristica) {

        return this.repository.save(CaracteristicaEntity.from(aCaracteristica)).toDomain();
    }

    @Override
    public void delete(Caracteristica aCaracteristica) {

        final var entity = CaracteristicaEntity.from(aCaracteristica);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
