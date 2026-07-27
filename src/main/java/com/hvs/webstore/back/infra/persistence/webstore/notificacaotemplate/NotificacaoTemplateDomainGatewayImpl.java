package com.hvs.webstore.back.infra.persistence.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateId;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class NotificacaoTemplateDomainGatewayImpl implements NotificacaoTemplateDomainGateway {

    private final NotificacaoTemplateJpaRepository repository;

    public NotificacaoTemplateDomainGatewayImpl(NotificacaoTemplateJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public NotificacaoTemplate create(NotificacaoTemplate aNotificacaoTemplate) {

        return this.repository.save(NotificacaoTemplateEntity.from(aNotificacaoTemplate)).toDomain();
    }

    @Override
    public Optional<NotificacaoTemplate> read(NotificacaoTemplateId aId) {

        return this.repository.findById(aId.getValue()).map(NotificacaoTemplateEntity::toDomain);
    }

    @Override
    public Optional<NotificacaoTemplate> readByUuid(NotificacaoTemplateUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(NotificacaoTemplateEntity::toDomain);
    }

    @Override
    public List<NotificacaoTemplate> readByTipo(String aTipoDesc) {

        return this.repository.findByTipoDesc(aTipoDesc).stream().map(NotificacaoTemplateEntity::toDomain).toList();
    }

    @Override
    public Pagination<NotificacaoTemplate> readAll(SearchQuery aQuery) {

        Page<NotificacaoTemplateEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<NotificacaoTemplateEntity> specification =
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
                pages.getContent().stream().map(NotificacaoTemplateEntity::toDomain).toList());
    }

    @Override
    public NotificacaoTemplate update(NotificacaoTemplate aNotificacaoTemplate) {

        return this.repository.save(NotificacaoTemplateEntity.from(aNotificacaoTemplate)).toDomain();
    }

    @Override
    public NotificacaoTemplate patch(NotificacaoTemplate aNotificacaoTemplate) {

        return this.repository.save(NotificacaoTemplateEntity.from(aNotificacaoTemplate)).toDomain();
    }

    @Override
    public void delete(NotificacaoTemplate aNotificacaoTemplate) {

        final var entity = NotificacaoTemplateEntity.from(aNotificacaoTemplate);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
