package com.hvs.webstore.back.infra.persistence.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoId;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class BoletoDomainGatewayImpl implements BoletoDomainGateway {

    private final BoletoJpaRepository repository;

    public BoletoDomainGatewayImpl(BoletoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Boleto create(Boleto aBoleto) {

        return this.repository.save(BoletoEntity.from(aBoleto)).toDomain();
    }

    @Override
    public Optional<Boleto> read(BoletoId aId) {

        return this.repository.findById(aId.getValue()).map(BoletoEntity::toDomain);
    }

    @Override
    public Optional<Boleto> readByUuid(BoletoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(BoletoEntity::toDomain);
    }

    @Override
    public Pagination<Boleto> readAll(SearchQuery aQuery) {

        Page<BoletoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<BoletoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("codigoBarras")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(BoletoEntity::toDomain).toList());
    }

    @Override
    public Boleto update(Boleto aBoleto) {

        return this.repository.save(BoletoEntity.from(aBoleto)).toDomain();
    }

    @Override
    public Boleto patch(Boleto aBoleto) {

        return this.repository.save(BoletoEntity.from(aBoleto)).toDomain();
    }

    @Override
    public void delete(Boleto aBoleto) {

        final var entity = BoletoEntity.from(aBoleto);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
