package com.hvs.webstore.back.infra.persistence.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoId;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class VideoDomainGatewayImpl implements VideoDomainGateway {

    private final VideoJpaRepository repository;

    public VideoDomainGatewayImpl(VideoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Video create(Video aVideo) {

        return this.repository.save(VideoEntity.from(aVideo)).toDomain();
    }

    @Override
    public Optional<Video> read(VideoId aId) {

        return this.repository.findById(aId.getValue()).map(VideoEntity::toDomain);
    }

    @Override
    public Optional<Video> readByUuid(VideoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(VideoEntity::toDomain);
    }

    @Override
    public Pagination<Video> readAll(SearchQuery aQuery) {

        Page<VideoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<VideoEntity> specification =
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
                pages.getContent().stream().map(VideoEntity::toDomain).toList());
    }

    @Override
    public Video update(Video aVideo) {

        return this.repository.save(VideoEntity.from(aVideo)).toDomain();
    }

    @Override
    public Video patch(Video aVideo) {

        return this.repository.save(VideoEntity.from(aVideo)).toDomain();
    }

    @Override
    public void delete(Video aVideo) {

        final var entity = VideoEntity.from(aVideo);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
