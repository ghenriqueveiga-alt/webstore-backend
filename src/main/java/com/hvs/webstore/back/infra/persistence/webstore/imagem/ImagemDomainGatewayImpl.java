package com.hvs.webstore.back.infra.persistence.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemId;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ImagemDomainGatewayImpl implements ImagemDomainGateway {

    private final ImagemJpaRepository repository;

    public ImagemDomainGatewayImpl(ImagemJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Imagem create(Imagem aImagem) {

        return this.repository.save(ImagemEntity.from(aImagem)).toDomain();
    }

    @Override
    public Optional<Imagem> read(ImagemId aId) {

        return this.repository.findById(aId.getValue()).map(ImagemEntity::toDomain);
    }

    @Override
    public Optional<Imagem> readByUuid(ImagemUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(ImagemEntity::toDomain);
    }

    @Override
    public Pagination<Imagem> readAll(SearchQuery aQuery) {

        Page<ImagemEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<ImagemEntity> specification =
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
                pages.getContent().stream().map(ImagemEntity::toDomain).toList());
    }

    @Override
    public Imagem update(Imagem aImagem) {

        return this.repository.save(ImagemEntity.from(aImagem)).toDomain();
    }

    @Override
    public Imagem patch(Imagem aImagem) {

        return this.repository.save(ImagemEntity.from(aImagem)).toDomain();
    }

    @Override
    public void delete(Imagem aImagem) {

        final var entity = ImagemEntity.from(aImagem);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
