package com.hvs.webstore.back.infra.persistence.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoId;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class FormaPagamentoDomainGatewayImpl implements FormaPagamentoDomainGateway {

    private final FormaPagamentoJpaRepository repository;

    public FormaPagamentoDomainGatewayImpl(FormaPagamentoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public FormaPagamento create(FormaPagamento aFormaPagamento) {

        return this.repository.save(FormaPagamentoEntity.from(aFormaPagamento)).toDomain();
    }

    @Override
    public Optional<FormaPagamento> read(FormaPagamentoId aId) {

        return this.repository.findById(aId.getValue()).map(FormaPagamentoEntity::toDomain);
    }

    @Override
    public Optional<FormaPagamento> readByUuid(FormaPagamentoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(FormaPagamentoEntity::toDomain);
    }

    @Override
    public Pagination<FormaPagamento> readAll(SearchQuery aQuery) {

        Page<FormaPagamentoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<FormaPagamentoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("cartao").get("nomeTitular")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(FormaPagamentoEntity::toDomain).toList());
    }

    @Override
    public FormaPagamento update(FormaPagamento aFormaPagamento) {

        return this.repository.save(FormaPagamentoEntity.from(aFormaPagamento)).toDomain();
    }

    @Override
    public FormaPagamento patch(FormaPagamento aFormaPagamento) {

        return this.repository.save(FormaPagamentoEntity.from(aFormaPagamento)).toDomain();
    }

    @Override
    public void delete(FormaPagamento aFormaPagamento) {

        final var entity = FormaPagamentoEntity.from(aFormaPagamento);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
