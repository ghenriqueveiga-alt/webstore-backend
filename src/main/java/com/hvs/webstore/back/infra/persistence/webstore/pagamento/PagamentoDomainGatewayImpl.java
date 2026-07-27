package com.hvs.webstore.back.infra.persistence.webstore.pagamento;

import com.hvs.webstore.back.domain.entity.webstore.pagamento.Pagamento;
import com.hvs.webstore.back.domain.entity.webstore.pagamento.PagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pagamento.PagamentoId;
import com.hvs.webstore.back.domain.entity.webstore.pagamento.PagamentoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class PagamentoDomainGatewayImpl implements PagamentoDomainGateway {

    private final PagamentoJpaRepository repository;

    public PagamentoDomainGatewayImpl(PagamentoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Pagamento create(Pagamento aPagamento) {

        return this.repository.save(PagamentoEntity.from(aPagamento)).toDomain();
    }

    @Override
    public Optional<Pagamento> read(PagamentoId aId) {

        return this.repository.findById(aId.getValue()).map(PagamentoEntity::toDomain);
    }

    @Override
    public Optional<Pagamento> readByUuid(PagamentoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PagamentoEntity::toDomain);
    }

    @Override
    public Optional<Pagamento> readByPedidoId(Long aPedidoId) {

        return this.repository.findByPedidoId(aPedidoId).map(PagamentoEntity::toDomain);
    }

    @Override
    public Pagination<Pagamento> readAll(SearchQuery aQuery) {

        Page<PagamentoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        pages = this.repository.findAll(pageable);

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(PagamentoEntity::toDomain).toList());
    }

    @Override
    public Pagamento update(Pagamento aPagamento) {

        return this.repository.save(PagamentoEntity.from(aPagamento)).toDomain();
    }

    @Override
    public Pagamento patch(Pagamento aPagamento) {

        return this.repository.save(PagamentoEntity.from(aPagamento)).toDomain();
    }

    @Override
    public void delete(Pagamento aPagamento) {

        final var entity = PagamentoEntity.from(aPagamento);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
