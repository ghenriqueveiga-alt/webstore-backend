package com.hvs.webstore.back.infra.persistence.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoId;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class EnderecoDomainGatewayImpl implements EnderecoDomainGateway {

    private final EnderecoJpaRepository repository;

    public EnderecoDomainGatewayImpl(EnderecoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Endereco create(Endereco aEndereco) {

        return this.repository.save(EnderecoEntity.from(aEndereco)).toDomain();
    }

    @Override
    public Optional<Endereco> read(EnderecoId aId) {

        return this.repository.findById(aId.getValue()).map(EnderecoEntity::toDomain);
    }

    @Override
    public Optional<Endereco> readByUuid(EnderecoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(EnderecoEntity::toDomain);
    }

    @Override
    public Pagination<Endereco> readAll(SearchQuery aQuery) {

        Page<EnderecoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<EnderecoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("logradouro")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(EnderecoEntity::toDomain).toList());
    }

    @Override
    public Endereco update(Endereco aEndereco) {

        return this.repository.save(EnderecoEntity.from(aEndereco)).toDomain();
    }

    @Override
    public Endereco patch(Endereco aEndereco) {

        return this.repository.save(EnderecoEntity.from(aEndereco)).toDomain();
    }

    @Override
    public void delete(Endereco aEndereco) {

        final var entity = EnderecoEntity.from(aEndereco);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
