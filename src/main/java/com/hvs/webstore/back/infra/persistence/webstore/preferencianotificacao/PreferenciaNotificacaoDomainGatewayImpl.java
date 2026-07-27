package com.hvs.webstore.back.infra.persistence.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class PreferenciaNotificacaoDomainGatewayImpl implements PreferenciaNotificacaoDomainGateway {

    private final PreferenciaNotificacaoJpaRepository repository;

    public PreferenciaNotificacaoDomainGatewayImpl(PreferenciaNotificacaoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public PreferenciaNotificacao create(PreferenciaNotificacao aPreferenciaNotificacao) {

        return this.repository.save(PreferenciaNotificacaoEntity.from(aPreferenciaNotificacao)).toDomain();
    }

    @Override
    public Optional<PreferenciaNotificacao> read(PreferenciaNotificacaoId aId) {

        return this.repository.findById(aId.getValue()).map(PreferenciaNotificacaoEntity::toDomain);
    }

    @Override
    public Optional<PreferenciaNotificacao> readByUuid(PreferenciaNotificacaoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(PreferenciaNotificacaoEntity::toDomain);
    }

    @Override
    public List<PreferenciaNotificacao> readByUsuarioId(Long aUsuarioId) {

        return this.repository.findByUsuario_Id(aUsuarioId).stream().map(PreferenciaNotificacaoEntity::toDomain).toList();
    }

    @Override
    public Optional<PreferenciaNotificacao> readByUsuarioIdAndTipo(Long aUsuarioId, String aTipoDesc) {

        return this.repository.findByUsuario_IdAndTipoDesc(aUsuarioId, aTipoDesc).map(PreferenciaNotificacaoEntity::toDomain);
    }

    @Override
    public Pagination<PreferenciaNotificacao> readAll(SearchQuery aQuery) {

        Page<PreferenciaNotificacaoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<PreferenciaNotificacaoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("tipoCode")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(PreferenciaNotificacaoEntity::toDomain).toList());
    }

    @Override
    public PreferenciaNotificacao update(PreferenciaNotificacao aPreferenciaNotificacao) {

        return this.repository.save(PreferenciaNotificacaoEntity.from(aPreferenciaNotificacao)).toDomain();
    }

    @Override
    public PreferenciaNotificacao patch(PreferenciaNotificacao aPreferenciaNotificacao) {

        return this.repository.save(PreferenciaNotificacaoEntity.from(aPreferenciaNotificacao)).toDomain();
    }

    @Override
    public void delete(PreferenciaNotificacao aPreferenciaNotificacao) {

        final var entity = PreferenciaNotificacaoEntity.from(aPreferenciaNotificacao);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
