package com.hvs.webstore.back.domain.entity.webstore.permissao;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Permissao extends Entity<PermissaoId> {

    private final PermissaoUuid uuid;
    private final PermissaoStatus statusCode;
    private final String nome;
    private final String chave;
    private final String descricao;

    private Permissao(final PermissaoId id,
                      final PermissaoUuid uuid,
                      final PermissaoStatus statusCode,
                      final String nome,
                      final String chave,
                      final String descricao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.chave = chave;
        this.descricao = descricao;
    }

    public static Permissao create(final String aNome,
                                   final String aChave,
                                   final String aDescricao) {

        final var id = PermissaoId.from(-1L);
        final var uuid = PermissaoUuid.unique();
        final var status = PermissaoStatus.ACTIVE;

        return new Permissao(
                id,
                uuid,
                status,
                aNome,
                aChave,
                aDescricao);
    }

    public static Permissao update(final Long aId,
                                   final String aUuid,
                                   final String aStatusCode,
                                   final String aNome,
                                   final String aChave,
                                   final String aDescricao) {

        final var id = aId != null ? PermissaoId.from(aId) : null;
        final var uuid = aUuid != null ? PermissaoUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? PermissaoStatus.findByCode(aStatusCode) : null;

        return new Permissao(
                id,
                uuid,
                status,
                aNome,
                aChave,
                aDescricao);
    }

    public static Permissao patch(final String aStatusCode,
                                  final String aNome,
                                  final String aChave,
                                  final String aDescricao,
                                  final Permissao aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? PermissaoStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var nome = aNome != null ? aNome : aExisting.getNome();
        final var chave = aChave != null ? aChave : aExisting.getChave();
        final var descricao = aDescricao != null ? aDescricao : aExisting.getDescricao();

        return new Permissao(
                id,
                uuid,
                status,
                nome,
                chave,
                descricao);
    }

    public static Permissao from(final Long aId,
                                 final String aUuid,
                                 final String aStatusDesc,
                                 final String aNome,
                                 final String aChave,
                                 final String aDescricao) {

        final var id = aId != null ? PermissaoId.from(aId) : null;
        final var uuid = aUuid != null ? PermissaoUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? PermissaoStatus.findByDesc(aStatusDesc) : null;

        return new Permissao(
                id,
                uuid,
                status,
                aNome,
                aChave,
                aDescricao);
    }

    public static Permissao from(final Long aId) {

        return new Permissao(
                aId != null ? PermissaoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Permissao from(final String aUuid) {

        return new Permissao(
                null,
                aUuid != null ? PermissaoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new PermissaoValidator(aHandler, this).validate();
    }

    public PermissaoUuid getUuid() {
        return uuid;
    }
    public PermissaoStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getChave() {
        return chave;
    }
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Permissao that = (Permissao) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(chave, that.chave) &&
                Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                chave,
                descricao);
    }
}
