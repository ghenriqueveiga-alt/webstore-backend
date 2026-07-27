package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Estoque extends Entity<EstoqueId> {

    private final EstoqueUuid uuid;
    private final EstoqueStatus statusCode;
    private final Produto produto;
    private final Integer quantidade;
    private final Integer reservado;
    private final Integer quantidadeMinima;

    private Estoque(final EstoqueId id,
                    final EstoqueUuid uuid,
                    final EstoqueStatus statusCode,
                    final Produto produto,
                    final Integer quantidade,
                    final Integer reservado,
                    final Integer quantidadeMinima) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.produto = produto;
        this.quantidade = quantidade;
        this.reservado = reservado;
        this.quantidadeMinima = quantidadeMinima;
    }

    public static Estoque create(final Long aProdutoId,
                                 final Integer aQuantidade,
                                 final Integer aQuantidadeMinima) {

        return new Estoque(
                EstoqueId.from(-1L),
                EstoqueUuid.unique(),
                EstoqueStatus.ACTIVE,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                aQuantidade,
                0,
                aQuantidadeMinima);
    }

    public static Estoque update(final Long aId,
                                 final String aUuid,
                                 final String aStatusCode,
                                 final Long aProdutoId,
                                 final Integer aQuantidade,
                                 final Integer aReservado,
                                 final Integer aQuantidadeMinima) {

        return new Estoque(
                aId != null ? EstoqueId.from(aId) : null,
                aUuid != null ? EstoqueUuid.from(aUuid) : null,
                aStatusCode != null ? EstoqueStatus.findByCode(aStatusCode) : null,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                aQuantidade,
                aReservado,
                aQuantidadeMinima);
    }

    public static Estoque patch(final String aStatusCode,
                                final Long aProdutoId,
                                final Integer aQuantidade,
                                final Integer aReservado,
                                final Integer aQuantidadeMinima,
                                final Estoque aExisting) {

        return new Estoque(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? EstoqueStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aProdutoId != null ? Produto.from(aProdutoId) : aExisting.getProduto(),
                aQuantidade != null ? aQuantidade : aExisting.getQuantidade(),
                aReservado != null ? aReservado : aExisting.getReservado(),
                aQuantidadeMinima != null ? aQuantidadeMinima : aExisting.getQuantidadeMinima());
    }

    public static Estoque from(final Long aId,
                               final String aUuid,
                               final String aStatusDesc,
                               final Produto aProduto,
                               final Integer aQuantidade,
                               final Integer aReservado,
                               final Integer aQuantidadeMinima) {

        return new Estoque(
                aId != null ? EstoqueId.from(aId) : null,
                aUuid != null ? EstoqueUuid.from(aUuid) : null,
                aStatusDesc != null ? EstoqueStatus.findByDesc(aStatusDesc) : null,
                aProduto,
                aQuantidade,
                aReservado,
                aQuantidadeMinima);
    }

    public static Estoque from(final Long aId) {

        return new Estoque(
                aId != null ? EstoqueId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Estoque from(final String aUuid) {

        return new Estoque(
                null,
                aUuid != null ? EstoqueUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new EstoqueValidator(aHandler, this).validate();
    }

    public EstoqueUuid getUuid() {
        return uuid;
    }
    public EstoqueStatus getStatusCode() {
        return statusCode;
    }
    public Produto getProduto() {
        return produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public Integer getReservado() {
        return reservado;
    }
    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Estoque estoque = (Estoque) o;

        return Objects.equals(uuid, estoque.uuid) &&
                statusCode == estoque.statusCode &&
                Objects.equals(produto, estoque.produto) &&
                Objects.equals(quantidade, estoque.quantidade) &&
                Objects.equals(reservado, estoque.reservado) &&
                Objects.equals(quantidadeMinima, estoque.quantidadeMinima);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                produto,
                quantidade,
                reservado,
                quantidadeMinima);
    }
}
