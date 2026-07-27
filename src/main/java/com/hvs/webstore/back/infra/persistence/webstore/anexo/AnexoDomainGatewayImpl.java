package com.hvs.webstore.back.infra.persistence.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoId;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class AnexoDomainGatewayImpl implements AnexoDomainGateway {

    private final AnexoJpaRepository repository;

    public AnexoDomainGatewayImpl(AnexoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Anexo create(Anexo aAnexo) {

        return this.repository.save(AnexoEntity.from(aAnexo)).toDomain();
    }

    @Override
    public Optional<Anexo> read(AnexoId aId) {

        return this.repository.findById(aId.getValue()).map(AnexoEntity::toDomain);
    }

    @Override
    public Optional<Anexo> readByUuid(AnexoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(AnexoEntity::toDomain);
    }

    @Override
    public List<Anexo> readByEntidade(String aEntidadeNome) {

        return this.repository.findByEntidadeAndEntidadeId(aEntidadeNome, null).stream().map(AnexoEntity::toDomain).toList();
    }

    @Override
    public Pagination<Anexo> readAll(SearchQuery aQuery) {

        Page<AnexoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<AnexoEntity> specification =
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
                pages.getContent().stream().map(AnexoEntity::toDomain).toList());
    }

    @Override
    public Anexo update(Anexo aAnexo) {

        return this.repository.save(AnexoEntity.from(aAnexo)).toDomain();
    }

    @Override
    public Anexo patch(Anexo aAnexo) {

        return this.repository.save(AnexoEntity.from(aAnexo)).toDomain();
    }

    @Override
    public void delete(Anexo aAnexo) {

        final var entity = AnexoEntity.from(aAnexo);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
