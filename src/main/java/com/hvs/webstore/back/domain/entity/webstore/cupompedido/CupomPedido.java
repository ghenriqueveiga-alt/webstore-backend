package com.hvs.webstore.back.domain.entity.webstore.cupompedido;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class CupomPedido extends Entity<CupomPedidoId> {

    private final CupomPedidoUuid uuid;
    private final CupomPedidoStatus statusCode;
    private final Cupom cupom;
    private final Pedido pedido;
    private final Preco valorDesconto;

    private CupomPedido(final CupomPedidoId id,
                        final CupomPedidoUuid uuid,
                        final CupomPedidoStatus statusCode,
                        final Cupom cupom,
                        final Pedido pedido,
                        final Preco valorDesconto) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.cupom = cupom;
        this.pedido = pedido;
        this.valorDesconto = valorDesconto;
    }

    public static CupomPedido create(final Long aCupomId,
                                     final Long aPedidoId,
                                     final Long aValorDescontoId) {

        return new CupomPedido(
                CupomPedidoId.from(-1L),
                CupomPedidoUuid.unique(),
                CupomPedidoStatus.ACTIVE,
                aCupomId != null ? Cupom.from(aCupomId) : null,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aValorDescontoId != null ? Preco.from(aValorDescontoId) : null);
    }

    public static CupomPedido update(final Long aId,
                                     final String aUuid,
                                     final String aStatusCode,
                                     final Long aCupomId,
                                     final Long aPedidoId,
                                     final Long aValorDescontoId) {

        return new CupomPedido(
                aId != null ? CupomPedidoId.from(aId) : null,
                aUuid != null ? CupomPedidoUuid.from(aUuid) : null,
                aStatusCode != null ? CupomPedidoStatus.findByCode(aStatusCode) : null,
                aCupomId != null ? Cupom.from(aCupomId) : null,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aValorDescontoId != null ? Preco.from(aValorDescontoId) : null);
    }

    public static CupomPedido patch(final String aStatusCode,
                                    final Long aCupomId,
                                    final Long aPedidoId,
                                    final Long aValorDescontoId,
                                    final CupomPedido aExisting) {

        return new CupomPedido(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CupomPedidoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aCupomId != null ? Cupom.from(aCupomId) : aExisting.getCupom(),
                aPedidoId != null ? Pedido.from(aPedidoId) : aExisting.getPedido(),
                aValorDescontoId != null ? Preco.from(aValorDescontoId) : aExisting.getValorDesconto());
    }

    public static CupomPedido from(final Long aId,
                                   final String aUuid,
                                   final String aStatusDesc,
                                   final Cupom aCupom,
                                   final Pedido aPedido,
                                   final Preco aValorDesconto) {

        return new CupomPedido(
                aId != null ? CupomPedidoId.from(aId) : null,
                aUuid != null ? CupomPedidoUuid.from(aUuid) : null,
                aStatusDesc != null ? CupomPedidoStatus.findByDesc(aStatusDesc) : null,
                aCupom,
                aPedido,
                aValorDesconto);
    }

    public static CupomPedido from(final Long aId) {

        return new CupomPedido(
                aId != null ? CupomPedidoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static CupomPedido from(final String aUuid) {

        return new CupomPedido(
                null,
                aUuid != null ? CupomPedidoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CupomPedidoValidator(aHandler, this).validate();
    }

    public CupomPedidoUuid getUuid() {
        return uuid;
    }
    public CupomPedidoStatus getStatusCode() {
        return statusCode;
    }
    public Cupom getCupom() {
        return cupom;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public Preco getValorDesconto() {
        return valorDesconto;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        CupomPedido that = (CupomPedido) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(cupom, that.cupom) &&
                Objects.equals(pedido, that.pedido) &&
                Objects.equals(valorDesconto, that.valorDesconto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                cupom,
                pedido,
                valorDesconto);
    }
}
