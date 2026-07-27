package com.hvs.webstore.back.domain.entity.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class VariacaoProduto extends Entity<VariacaoProdutoId> {

    private final VariacaoProdutoUuid uuid;
    private final VariacaoProdutoStatus statusCode;
    private final Produto produto;
    private final String nome;
    private final String valor;
    private final String sku;
    private final Integer estoque;

    private VariacaoProduto(final VariacaoProdutoId id,
                             final VariacaoProdutoUuid uuid,
                             final VariacaoProdutoStatus statusCode,
                             final Produto produto,
                             final String nome,
                             final String valor,
                             final String sku,
                             final Integer estoque) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.produto = produto;
        this.nome = nome;
        this.valor = valor;
        this.sku = sku;
        this.estoque = estoque;
    }

    public static VariacaoProduto create(final Long aProdutoId,
                                          final String aNome,
                                          final String aValor,
                                          final String aSku,
                                          final Integer aEstoque) {

        return new VariacaoProduto(
                VariacaoProdutoId.from(-1L),
                VariacaoProdutoUuid.unique(),
                VariacaoProdutoStatus.ACTIVE,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                aNome,
                aValor,
                aSku,
                aEstoque);
    }

    public static VariacaoProduto update(final Long aId,
                                          final String aUuid,
                                          final String aStatusCode,
                                          final Long aProdutoId,
                                          final String aNome,
                                          final String aValor,
                                          final String aSku,
                                          final Integer aEstoque) {

        return new VariacaoProduto(
                aId != null ? VariacaoProdutoId.from(aId) : null,
                aUuid != null ? VariacaoProdutoUuid.from(aUuid) : null,
                aStatusCode != null ? VariacaoProdutoStatus.findByCode(aStatusCode) : null,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                aNome,
                aValor,
                aSku,
                aEstoque);
    }

    public static VariacaoProduto patch(final String aStatusCode,
                                         final Long aProdutoId,
                                         final String aNome,
                                         final String aValor,
                                         final String aSku,
                                         final Integer aEstoque,
                                         final VariacaoProduto aExisting) {

        return new VariacaoProduto(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? VariacaoProdutoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aProdutoId != null ? Produto.from(aProdutoId) : aExisting.getProduto(),
                aNome != null ? aNome : aExisting.getNome(),
                aValor != null ? aValor : aExisting.getValor(),
                aSku != null ? aSku : aExisting.getSku(),
                aEstoque != null ? aEstoque : aExisting.getEstoque());
    }

    public static VariacaoProduto from(final Long aId,
                                        final String aUuid,
                                        final String aStatusDesc,
                                        final Produto aProduto,
                                        final String aNome,
                                        final String aValor,
                                        final String aSku,
                                        final Integer aEstoque) {

        return new VariacaoProduto(
                aId != null ? VariacaoProdutoId.from(aId) : null,
                aUuid != null ? VariacaoProdutoUuid.from(aUuid) : null,
                aStatusDesc != null ? VariacaoProdutoStatus.findByDesc(aStatusDesc) : null,
                aProduto,
                aNome,
                aValor,
                aSku,
                aEstoque);
    }

    public static VariacaoProduto from(final Long aId) {

        return new VariacaoProduto(
                aId != null ? VariacaoProdutoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static VariacaoProduto from(final String aUuid) {

        return new VariacaoProduto(
                null,
                aUuid != null ? VariacaoProdutoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new VariacaoProdutoValidator(aHandler, this).validate();
    }

    public VariacaoProdutoUuid getUuid() {
        return uuid;
    }
    public VariacaoProdutoStatus getStatusCode() {
        return statusCode;
    }
    public Produto getProduto() {
        return produto;
    }
    public String getNome() {
        return nome;
    }
    public String getValor() {
        return valor;
    }
    public String getSku() {
        return sku;
    }
    public Integer getEstoque() {
        return estoque;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        VariacaoProduto that = (VariacaoProduto) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(sku, that.sku) &&
                Objects.equals(estoque, that.estoque);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                produto,
                nome,
                valor,
                sku,
                estoque);
    }
}
