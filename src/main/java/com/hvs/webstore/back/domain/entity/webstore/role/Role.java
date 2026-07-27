package com.hvs.webstore.back.domain.entity.webstore.role;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.List;
import java.util.Objects;

public class Role extends Entity<RoleId> {

    private final RoleUuid uuid;
    private final RoleStatus statusCode;
    private final String nome;
    private final String descricao;
    private final List<Permissao> permissoes;

    private Role(final RoleId id,
                  final RoleUuid uuid,
                  final RoleStatus statusCode,
                  final String nome,
                  final String descricao,
                  final List<Permissao> permissoes) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.descricao = descricao;
        this.permissoes = permissoes;
    }

    public static Role create(final String aNome,
                               final String aDescricao,
                               final List<Long> aPermissaoIds) {

        final var id = RoleId.from(-1L);
        final var uuid = RoleUuid.unique();
        final var status = RoleStatus.ACTIVE;
        final var permissoes = aPermissaoIds != null ? aPermissaoIds.stream().map(Permissao::from).toList() : null;

        return new Role(
                id,
                uuid,
                status,
                aNome,
                aDescricao,
                permissoes);
    }

    public static Role update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final String aNome,
                               final String aDescricao,
                               final List<Long> aPermissaoIds) {

        final var id = aId != null ? RoleId.from(aId) : null;
        final var uuid = aUuid != null ? RoleUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? RoleStatus.findByCode(aStatusCode) : null;
        final var permissoes = aPermissaoIds != null ? aPermissaoIds.stream().map(Permissao::from).toList() : null;

        return new Role(
                id,
                uuid,
                status,
                aNome,
                aDescricao,
                permissoes);
    }

    public static Role patch(final String aStatusCode,
                              final String aNome,
                              final String aDescricao,
                              final List<Long> aPermissaoIds,
                              final Role aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? RoleStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var nome = aNome != null ? aNome : aExisting.getNome();
        final var descricao = aDescricao != null ? aDescricao : aExisting.getDescricao();
        final var permissoes = aPermissaoIds != null ? aPermissaoIds.stream().map(Permissao::from).toList() : aExisting.getPermissoes();

        return new Role(
                id,
                uuid,
                status,
                nome,
                descricao,
                permissoes);
    }

    public static Role from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final String aNome,
                             final String aDescricao,
                             final List<Permissao> aPermissoes) {

        final var id = aId != null ? RoleId.from(aId) : null;
        final var uuid = aUuid != null ? RoleUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? RoleStatus.findByDesc(aStatusDesc) : null;

        return new Role(
                id,
                uuid,
                status,
                aNome,
                aDescricao,
                aPermissoes);
    }

    public static Role from(final Long aId) {

        return new Role(
                aId != null ? RoleId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Role from(final String aUuid) {

        return new Role(
                null,
                aUuid != null ? RoleUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new RoleValidator(aHandler, this).validate();
    }

    public RoleUuid getUuid() {
        return uuid;
    }
    public RoleStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Role role = (Role) o;

        return Objects.equals(uuid, role.uuid) &&
                statusCode == role.statusCode &&
                Objects.equals(nome, role.nome) &&
                Objects.equals(descricao, role.descricao) &&
                Objects.equals(permissoes, role.permissoes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                descricao,
                permissoes);
    }
}
