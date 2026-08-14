package com.hvs.webstore.back.infra.persistence.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.EpisodioSearchQuery;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioId;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class EpisodioDomainGatewayImpl implements EpisodioDomainGateway {

    private final EpisodioJpaRepository repository;

    public EpisodioDomainGatewayImpl(EpisodioJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Episodio create(Episodio aEpisodio) {

        return this.repository.save(EpisodioEntity.from(aEpisodio)).toDomain();
    }

    @Override
    public Optional<Episodio> read(EpisodioId aId) {

        return this.repository.findById(aId.getValue()).map(EpisodioEntity::toDomain);
    }

    @Override
    public Optional<Episodio> readByUuid(EpisodioUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(EpisodioEntity::toDomain);
    }

    @Override
    public Pagination<Episodio> readAll(EpisodioSearchQuery aQuery) {

        Page<EpisodioEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<EpisodioEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(EpisodioEntity::toDomain).toList());
    }

    @Override
    public List<Episodio> readByPrograma(Long aProgramaId) {

        if (aProgramaId == null) {
            return List.of();
        }

        return this.repository.findByProgramaId(aProgramaId)
                .stream().map(EpisodioEntity::toDomainChildren).toList();
    }

    @Override
    public Episodio update(Episodio aEpisodio) {

        return this.repository.save(EpisodioEntity.from(aEpisodio)).toDomain();
    }

    @Override
    public Episodio patch(Episodio aEpisodio) {

        return this.repository.save(EpisodioEntity.from(aEpisodio)).toDomain();
    }

    @Override
    public void delete(Episodio aEpisodio) {

        final var entity = EpisodioEntity.from(aEpisodio);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
