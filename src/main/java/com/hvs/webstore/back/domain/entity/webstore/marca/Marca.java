package com.hvs.webstore.back.domain.entity.webstore.marca;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.List;
import java.util.Objects;

public class Marca extends Entity<MarcaId> {

    private final MarcaUuid uuid;
    private final MarcaStatus statusCode;
    private final String nome;
    private final String descricao;
    private final List<Produto> produtos;

    private Marca(final MarcaId id,
                  final MarcaUuid uuid,
                  final MarcaStatus statusCode,
                  final String nome,
                  final String descricao,
                  final List<Produto> produtos) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.descricao = descricao;
        this.produtos = produtos;
    }

    public static Marca create(final String aNome,
                               final String aDescricao,
                               final List<Long> aProdutoIds) {

        return new Marca(
                MarcaId.from(-1L),
                MarcaUuid.unique(),
                MarcaStatus.ACTIVE,
                aNome,
                aDescricao,
                aProdutoIds != null ? aProdutoIds.stream().map(Produto::from).toList() : null);
    }

    public static Marca update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final String aNome,
                               final String aDescricao,
                               final List<Long> aProdutoIds) {

        return new Marca(
                aId != null ? MarcaId.from(aId) : null,
                aUuid != null ? MarcaUuid.from(aUuid) : null,
                aStatusCode != null ? MarcaStatus.findByCode(aStatusCode) : null,
                aNome,
                aDescricao,
                aProdutoIds != null ? aProdutoIds.stream().map(Produto::from).toList() : null);
    }

    public static Marca patch(final String aStatusCode,
                              final String aNome,
                              final String aDescricao,
                              final List<Long> aProdutoIds,
                              final Marca aExisting) {

        return new Marca(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? MarcaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aDescricao != null ? aDescricao : aExisting.getDescricao(),
                aProdutoIds != null ? aProdutoIds.stream().map(Produto::from).toList() : aExisting.getProdutos());
    }

    public static Marca from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final String aNome,
                             final String aDescricao,
                             final List<Produto> aProdutos) {

        return new Marca(
                aId != null ? MarcaId.from(aId) : null,
                aUuid != null ? MarcaUuid.from(aUuid) : null,
                aStatusDesc != null ? MarcaStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aDescricao,
                aProdutos);
    }

    public static Marca from(final Long aId) {

        return new Marca(
                aId != null ? MarcaId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Marca from(final String aUuid) {

        return new Marca(
                null,
                aUuid != null ? MarcaUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new MarcaValidator(aHandler, this).validate();
    }

    public MarcaUuid getUuid() {
        return uuid;
    }
    public MarcaStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Marca that = (Marca) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(produtos, that.produtos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                descricao,
                produtos);
    }
}
