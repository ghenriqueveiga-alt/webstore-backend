package com.hvs.webstore.back.infra.persistence.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class UsuarioDomainGatewayImpl implements UsuarioDomainGateway {

    private final UsuarioJpaRepository repository;

    public UsuarioDomainGatewayImpl(UsuarioJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Usuario create(Usuario aUsuario) {

        return this.repository.save(UsuarioEntity.from(aUsuario)).toDomain();
    }

    @Override
    public Optional<Usuario> read(UsuarioId aId) {

        return this.repository.findById(aId.getValue()).map(UsuarioEntity::toDomain);
    }

    @Override
    public Optional<Usuario> readByUuid(UsuarioUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(UsuarioEntity::toDomain);
    }

    @Override
    public Optional<Usuario> readByEmail(String aEmail) {

        return this.repository.findByEmail(aEmail).map(UsuarioEntity::toDomain);
    }

    @Override
    public Pagination<Usuario> readAll(SearchQuery aQuery) {

        Page<UsuarioEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<UsuarioEntity> specification =
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
                pages.getContent().stream().map(UsuarioEntity::toDomain).toList());
    }

    @Override
    public Usuario update(Usuario aUsuario) {

        return this.repository.save(UsuarioEntity.from(aUsuario)).toDomain();
    }

    @Override
    public Usuario patch(Usuario aUsuario) {

        return this.repository.save(UsuarioEntity.from(aUsuario)).toDomain();
    }

    @Override
    public void delete(Usuario aUsuario) {

        final var entity = UsuarioEntity.from(aUsuario);
        entity.setStatusDesc("Deleted");
        this.repository.save(entity);
    }
}
