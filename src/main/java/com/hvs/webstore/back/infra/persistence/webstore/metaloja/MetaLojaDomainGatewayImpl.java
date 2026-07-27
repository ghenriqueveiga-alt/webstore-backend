package com.hvs.webstore.back.infra.persistence.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaId;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class MetaLojaDomainGatewayImpl implements MetaLojaDomainGateway {

    private final MetaLojaJpaRepository repository;

    public MetaLojaDomainGatewayImpl(MetaLojaJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public MetaLoja create(MetaLoja aMetaLoja) {

        return this.repository.save(MetaLojaEntity.from(aMetaLoja)).toDomain();
    }

    @Override
    public Optional<MetaLoja> read(MetaLojaId aId) {

        return this.repository.findById(aId.getValue()).map(MetaLojaEntity::toDomain);
    }

    @Override
    public Optional<MetaLoja> readByUuid(MetaLojaUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(MetaLojaEntity::toDomain);
    }

    @Override
    public Optional<MetaLoja> readByChave(String aChave) {

        return this.repository.findByChave(aChave).map(MetaLojaEntity::toDomain);
    }

    @Override
    public Pagination<MetaLoja> readAll(SearchQuery aQuery) {

        Page<MetaLojaEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<MetaLojaEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("chave")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(MetaLojaEntity::toDomain).toList());
    }

    @Override
    public MetaLoja update(MetaLoja aMetaLoja) {

        return this.repository.save(MetaLojaEntity.from(aMetaLoja)).toDomain();
    }

    @Override
    public MetaLoja patch(MetaLoja aMetaLoja) {

        return this.repository.save(MetaLojaEntity.from(aMetaLoja)).toDomain();
    }

    @Override
    public void delete(MetaLoja aMetaLoja) {

        final var entity = MetaLojaEntity.from(aMetaLoja);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
