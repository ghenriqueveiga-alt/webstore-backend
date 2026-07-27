package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class ItemCarrinho extends Entity<ItemCarrinhoId> {

    private final ItemCarrinhoUuid uuid;
    private final ItemCarrinhoStatus statusCode;
    private final Carrinho carrinho;
    private final Produto produto;
    private final Preco preco;
    private final Integer quantidade;

    private ItemCarrinho(final ItemCarrinhoId id,
                         final ItemCarrinhoUuid uuid,
                         final ItemCarrinhoStatus statusCode,
                         final Carrinho carrinho,
                         final Produto produto,
                         final Preco preco,
                         final Integer quantidade) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.carrinho = carrinho;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public static ItemCarrinho create(final Long aProdutoId,
                                       final Integer aQuantidade) {

        return new ItemCarrinho(
                ItemCarrinhoId.from(-1L),
                ItemCarrinhoUuid.unique(),
                ItemCarrinhoStatus.ACTIVE,
                null,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                null,
                aQuantidade);
    }

    public static ItemCarrinho from(final Long aId,
                                     final String aUuid,
                                     final String aStatusCode,
                                     final Produto aProduto,
                                     final Integer aQuantidade) {

        return new ItemCarrinho(
                aId != null ? ItemCarrinhoId.from(aId) : null,
                aUuid != null ? ItemCarrinhoUuid.from(aUuid) : null,
                aStatusCode != null ? ItemCarrinhoStatus.findByCode(aStatusCode) : null,
                null,
                aProduto,
                null,
                aQuantidade);
    }

    public static ItemCarrinho from(final Long aId) {

        return new ItemCarrinho(
                aId != null ? ItemCarrinhoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static ItemCarrinho from(final String aUuid) {

        return new ItemCarrinho(
                null,
                aUuid != null ? ItemCarrinhoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new ItemCarrinhoValidator(aHandler, this).validate();
    }

    public ItemCarrinhoUuid getUuid() {
        return uuid;
    }
    public ItemCarrinhoStatus getStatusCode() {
        return statusCode;
    }
    public Carrinho getCarrinho() {
        return carrinho;
    }
    public Produto getProduto() {
        return produto;
    }
    public Preco getPreco() {
        return preco;
    }
    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        ItemCarrinho that = (ItemCarrinho) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(carrinho, that.carrinho) &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                carrinho,
                produto,
                preco,
                quantidade);
    }
}
