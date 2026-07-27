package com.hvs.webstore.back.domain.entity.webstore.categoria;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Categoria extends Entity<CategoriaId> {

    private final CategoriaUuid uuid;
    private final CategoriaStatus statusCode;
    private final String nome;
    private final String descricao;
    private final List<Produto> produtos;

    private Categoria(final CategoriaId id,
                      final CategoriaUuid uuid,
                      final CategoriaStatus statusCode,
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

    public static Categoria create(final String aNome,
                                   final String aDescricao,
                                   final List<Long> aProdutoIds) {

        final List<Produto> produtos = aProdutoIds != null ? aProdutoIds.stream()
                .map(Produto::from).toList() : Collections.emptyList();

        return new Categoria(
                CategoriaId.from(-1L),
                CategoriaUuid.unique(),
                CategoriaStatus.ACTIVE,
                aNome,
                aDescricao,
                produtos);
    }

    public static Categoria update(final Long aId,
                                   final String aUuid,
                                   final String aStatusCode,
                                   final String aNome,
                                   final String aDescricao,
                                   final List<Long> aProdutoIds) {

        final List<Produto> produtos = aProdutoIds != null ? aProdutoIds.stream()
                .map(Produto::from).toList() : Collections.emptyList();

        return new Categoria(
                aId != null ? CategoriaId.from(aId) : null,
                aUuid != null ? CategoriaUuid.from(aUuid) : null,
                aStatusCode != null ? CategoriaStatus.findByCode(aStatusCode) : null,
                aNome,
                aDescricao,
                produtos);
    }

    public static Categoria patch(final String aStatusCode,
                                  final String aNome,
                                  final String aDescricao,
                                  final List<Long> aProdutoIds,
                                  final Categoria aExisting) {

        final List<Produto> produtos = aProdutoIds != null ? aProdutoIds.stream()
                .map(Produto::from).toList() : aExisting.getProdutos();

        return new Categoria(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CategoriaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aDescricao != null ? aDescricao : aExisting.getDescricao(),
                produtos);
    }

    public static Categoria from(final Long aId,
                                 final String aUuid,
                                 final String aStatusDesc,
                                 final String aNome,
                                 final String aDescricao,
                                 final List<Produto> aProdutos) {

        return new Categoria(
                aId != null ? CategoriaId.from(aId) : null,
                aUuid != null ? CategoriaUuid.from(aUuid) : null,
                aStatusDesc != null ? CategoriaStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aDescricao,
                aProdutos);
    }

    public static Categoria from(final Long aId) {

        return new Categoria(
                aId != null ? CategoriaId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Categoria from(final String aUuid) {

        return new Categoria(
                null,
                aUuid != null ? CategoriaUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CategoriaValidator(aHandler, this).validate();
    }

    public CategoriaUuid getUuid() {
        return uuid;
    }
    public CategoriaStatus getStatusCode() {
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

        Categoria that = (Categoria) o;

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
