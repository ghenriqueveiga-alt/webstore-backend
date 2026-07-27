package com.hvs.webstore.back.domain.entity.webstore.historicopedido;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class HistoricoPedido extends Entity<HistoricoPedidoId> {

    private final HistoricoPedidoUuid uuid;
    private final HistoricoPedidoStatus statusCode;
    private final Pedido pedido;
    private final String statusAnterior;
    private final String statusNovo;
    private final String observacao;
    private final String criadoPor;
    private final Instant dataCriacao;

    private HistoricoPedido(final HistoricoPedidoId id,
                            final HistoricoPedidoUuid uuid,
                            final HistoricoPedidoStatus statusCode,
                            final Pedido pedido,
                            final String statusAnterior,
                            final String statusNovo,
                            final String observacao,
                            final String criadoPor,
                            final Instant dataCriacao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.pedido = pedido;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.observacao = observacao;
        this.criadoPor = criadoPor;
        this.dataCriacao = dataCriacao;
    }

    public static HistoricoPedido create(final Long aPedidoId,
                                         final String aStatusAnterior,
                                         final String aStatusNovo,
                                         final String aObservacao,
                                         final String aCriadoPor) {

        return new HistoricoPedido(
                HistoricoPedidoId.from(-1L),
                HistoricoPedidoUuid.unique(),
                HistoricoPedidoStatus.ACTIVE,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aStatusAnterior,
                aStatusNovo,
                aObservacao,
                aCriadoPor,
                Instant.now());
    }

    public static HistoricoPedido createHistoricoPedido(final Long aPedidoId,
                                                        final String aStatusNovo) {

        return new HistoricoPedido(
                HistoricoPedidoId.from(-1L),
                HistoricoPedidoUuid.unique(),
                HistoricoPedidoStatus.ACTIVE,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                null,
                aStatusNovo,
                null,
                null,
                Instant.now());
    }

    public static HistoricoPedido from(final Long aId,
                                       final String aUuid,
                                       final String aStatusCode,
                                       final Pedido aPedido,
                                       final String aStatusAnterior,
                                       final String aStatusNovo,
                                       final String aObservacao,
                                       final String aCriadoPor,
                                       final Instant aDataCriacao) {

        return new HistoricoPedido(
                aId != null ? HistoricoPedidoId.from(aId) : null,
                aUuid != null ? HistoricoPedidoUuid.from(aUuid) : null,
                aStatusCode != null ? HistoricoPedidoStatus.findByCode(aStatusCode) : null,
                aPedido,
                aStatusAnterior,
                aStatusNovo,
                aObservacao,
                aCriadoPor,
                aDataCriacao);
    }

    public static HistoricoPedido from(final Long aId) {

        return new HistoricoPedido(
                aId != null ? HistoricoPedidoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static HistoricoPedido from(final String aUuid) {

        return new HistoricoPedido(
                null,
                aUuid != null ? HistoricoPedidoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new HistoricoPedidoValidator(aHandler, this).validate();
    }

    public HistoricoPedidoUuid getUuid() {
        return uuid;
    }
    public HistoricoPedidoStatus getStatusCode() {
        return statusCode;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public String getStatusAnterior() {
        return statusAnterior;
    }
    public String getStatusNovo() {
        return statusNovo;
    }
    public String getObservacao() {
        return observacao;
    }
    public String getCriadoPor() {
        return criadoPor;
    }
    public Instant getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        HistoricoPedido that = (HistoricoPedido) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(pedido, that.pedido) &&
                Objects.equals(statusAnterior, that.statusAnterior) &&
                Objects.equals(statusNovo, that.statusNovo) &&
                Objects.equals(observacao, that.observacao) &&
                Objects.equals(criadoPor, that.criadoPor) &&
                Objects.equals(dataCriacao, that.dataCriacao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                pedido,
                statusAnterior,
                statusNovo,
                observacao,
                criadoPor,
                dataCriacao);
    }
}
