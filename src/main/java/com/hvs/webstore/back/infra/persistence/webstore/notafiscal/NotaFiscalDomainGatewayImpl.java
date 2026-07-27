package com.hvs.webstore.back.infra.persistence.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalId;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class NotaFiscalDomainGatewayImpl implements NotaFiscalDomainGateway {

    private final NotaFiscalJpaRepository repository;

    public NotaFiscalDomainGatewayImpl(NotaFiscalJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public NotaFiscal create(NotaFiscal aNotaFiscal) {

        return this.repository.save(NotaFiscalEntity.from(aNotaFiscal)).toDomain();
    }

    @Override
    public Optional<NotaFiscal> read(NotaFiscalId aId) {

        return this.repository.findById(aId.getValue()).map(NotaFiscalEntity::toDomain);
    }

    @Override
    public Optional<NotaFiscal> readByUuid(NotaFiscalUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(NotaFiscalEntity::toDomain);
    }

    @Override
    public Optional<NotaFiscal> readByChaveAcesso(String aChaveAcesso) {

        return this.repository.findByChaveAcesso(aChaveAcesso).map(NotaFiscalEntity::toDomain);
    }

    @Override
    public Optional<NotaFiscal> readByPedidoId(Long aPedidoId) {

        return this.repository.findByPedidoId(aPedidoId).map(NotaFiscalEntity::toDomain);
    }

    @Override
    public Pagination<NotaFiscal> readAll(SearchQuery aQuery) {

        Page<NotaFiscalEntity> pages;
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
                pages.getContent().stream().map(NotaFiscalEntity::toDomain).toList());
    }

    @Override
    public NotaFiscal update(NotaFiscal aNotaFiscal) {

        return this.repository.save(NotaFiscalEntity.from(aNotaFiscal)).toDomain();
    }

    @Override
    public NotaFiscal patch(NotaFiscal aNotaFiscal) {

        return this.repository.save(NotaFiscalEntity.from(aNotaFiscal)).toDomain();
    }

    @Override
    public void delete(NotaFiscal aNotaFiscal) {

        final var entity = NotaFiscalEntity.from(aNotaFiscal);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
