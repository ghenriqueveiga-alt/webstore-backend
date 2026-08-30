package com.hvs.webstore.back.domain.entity.television.genero;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Genero extends Entity<GeneroId> {

    private final GeneroUuid uuid;
    private final GeneroStatus status;
    private final String nome;
    private final String descricao;

    private Genero(final GeneroId id,
                  final GeneroUuid uuid,
                  final GeneroStatus status,
                  final String nome,
                  final String descricao) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static Genero create(final String aNome,
                                final String aDescricao) {

        return new Genero(
                GeneroId.from(-1L),
                GeneroUuid.unique(),
                GeneroStatus.ACTIVE,
                aNome,
                aDescricao);
    }

    public static Genero update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final String aNome,
                                final String aDescricao) {

        return new Genero(
                aId != null ? GeneroId.from(aId) : null,
                aUuid != null ? GeneroUuid.from(aUuid) : null,
                aStatusCode != null ? GeneroStatus.findByCode(aStatusCode) : null,
                aNome,
                aDescricao);
    }

    public static Genero patch(final String aStatusCode,
                               final String aNome,
                               final String aDescricao,
                               final Genero aGeneroDB) {

        return new Genero(
                aGeneroDB.getId(),
                aGeneroDB.getUuid(),
                aStatusCode != null ? GeneroStatus.findByCode(aStatusCode) : aGeneroDB.getStatus(),
                aNome != null ? aNome : aGeneroDB.getNome(),
                aDescricao != null ? aDescricao : aGeneroDB.getDescricao());
    }

    public static Genero from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final String aNome,
                              final String aDescricao) {

        return new Genero(
                aId != null ? GeneroId.from(aId) : null,
                aUuid != null ? GeneroUuid.from(aUuid) : null,
                aStatusDesc != null ? GeneroStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aDescricao);
    }

    public static Genero from(final Long aId) {

        return new Genero(
                aId != null ? GeneroId.from(aId) : null,
                null,
                null,
                null,
                null);
    }

    public static Genero from(final String aUuid) {

        return new Genero(
                null,
                aUuid != null ? GeneroUuid.from(aUuid) : null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new GeneroValidator(aHandler, this).validate();
    }

    public GeneroUuid getUuid() { return uuid; }
    public GeneroStatus getStatus() { return status; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Genero genero = (Genero) o;

        return Objects.equals(uuid, genero.uuid) &&
                status == genero.status &&
                Objects.equals(nome, genero.nome) &&
                Objects.equals(descricao, genero.descricao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                nome,
                descricao);
    }
}
