package com.hvs.webstore.back.infra.persistence.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaId;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class LogAuditoriaDomainGatewayImpl implements LogAuditoriaDomainGateway {

    private final LogAuditoriaJpaRepository repository;

    public LogAuditoriaDomainGatewayImpl(LogAuditoriaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public LogAuditoria create(LogAuditoria aLogAuditoria) {

        return this.repository.save(LogAuditoriaEntity.from(aLogAuditoria)).toDomain();
    }

    @Override
    public Optional<LogAuditoria> read(LogAuditoriaId aId) {

        return this.repository.findById(aId.getValue()).map(LogAuditoriaEntity::toDomain);
    }

    @Override
    public Optional<LogAuditoria> readByUuid(LogAuditoriaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(LogAuditoriaEntity::toDomain);
    }

    @Override
    public List<LogAuditoria> readByEntidade(String aEntidadeNome) {

        return this.repository.findByEntidadeAndEntidadeId(aEntidadeNome, null).stream().map(LogAuditoriaEntity::toDomain).toList();
    }

    @Override
    public List<LogAuditoria> readByUsuarioId(Long aUsuarioId) {

        return this.repository.findByUsuario_Id(aUsuarioId).stream().map(LogAuditoriaEntity::toDomain).toList();
    }

    @Override
    public Pagination<LogAuditoria> readAll(SearchQuery aQuery) {

        Page<LogAuditoriaEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<LogAuditoriaEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("entidade")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(LogAuditoriaEntity::toDomain).toList());
    }

    @Override
    public LogAuditoria update(LogAuditoria aLogAuditoria) {

        return this.repository.save(LogAuditoriaEntity.from(aLogAuditoria)).toDomain();
    }

    @Override
    public LogAuditoria patch(LogAuditoria aLogAuditoria) {

        return this.repository.save(LogAuditoriaEntity.from(aLogAuditoria)).toDomain();
    }

    @Override
    public void delete(LogAuditoria aLogAuditoria) {

        final var entity = LogAuditoriaEntity.from(aLogAuditoria);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
