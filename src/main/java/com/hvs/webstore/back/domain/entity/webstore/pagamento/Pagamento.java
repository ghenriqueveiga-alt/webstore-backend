package com.hvs.webstore.back.domain.entity.webstore.pagamento;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Pagamento extends Entity<PagamentoId> {

    private final PagamentoUuid uuid;
    private final PagamentoStatus statusCode;
    private final Pedido pedido;
    private final Long valor;
    private final StatusTransacao statusTransacao;
    private final String gatewayTransacaoId;
    private final String gatewayResponse;
    private final Instant dataProcessamento;
    private final Instant dataExpiracao;

    private Pagamento(final PagamentoId id,
                      final PagamentoUuid uuid,
                      final PagamentoStatus statusCode,
                      final Pedido pedido,
                      final Long valor,
                      final StatusTransacao statusTransacao,
                      final String gatewayTransacaoId,
                      final String gatewayResponse,
                      final Instant dataProcessamento,
                      final Instant dataExpiracao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.pedido = pedido;
        this.valor = valor;
        this.statusTransacao = statusTransacao;
        this.gatewayTransacaoId = gatewayTransacaoId;
        this.gatewayResponse = gatewayResponse;
        this.dataProcessamento = dataProcessamento;
        this.dataExpiracao = dataExpiracao;
    }

    public static Pagamento create(final Long aPedidoId,
                                   final Long aValor) {

        return new Pagamento(
                PagamentoId.from(-1L),
                PagamentoUuid.unique(),
                PagamentoStatus.ACTIVE,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aValor,
                null,
                null,
                null,
                null,
                null);
    }

    public static Pagamento update(final Long aId,
                                   final String aUuid,
                                   final String aStatusCode,
                                   final Long aPedidoId,
                                   final Long aValor,
                                   final String aStatusTransacaoCode,
                                   final String aGatewayTransacaoId,
                                   final String aGatewayResponse,
                                   final Instant aDataProcessamento,
                                   final Instant aDataExpiracao) {

        return new Pagamento(
                aId != null ? PagamentoId.from(aId) : null,
                aUuid != null ? PagamentoUuid.from(aUuid) : null,
                aStatusCode != null ? PagamentoStatus.findByCode(aStatusCode) : null,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aValor,
                aStatusTransacaoCode != null ? StatusTransacao.findByCode(aStatusTransacaoCode) : null,
                aGatewayTransacaoId,
                aGatewayResponse,
                aDataProcessamento,
                aDataExpiracao);
    }

    public static Pagamento from(final Long aId,
                                 final String aUuid,
                                 final String aStatusDesc,
                                 final Pedido aPedido,
                                 final Long aValor,
                                 final String aStatusTransacaoDesc,
                                 final String aGatewayTransacaoId,
                                 final String aGatewayResponse,
                                 final Instant aDataProcessamento,
                                 final Instant aDataExpiracao) {

        return new Pagamento(
                aId != null ? PagamentoId.from(aId) : null,
                aUuid != null ? PagamentoUuid.from(aUuid) : null,
                aStatusDesc != null ? PagamentoStatus.findByDesc(aStatusDesc) : null,
                aPedido,
                aValor,
                aStatusTransacaoDesc != null ? StatusTransacao.findByDesc(aStatusTransacaoDesc) : null,
                aGatewayTransacaoId,
                aGatewayResponse,
                aDataProcessamento,
                aDataExpiracao);
    }

    public static Pagamento from(final Long aId) {

        return new Pagamento(
                aId != null ? PagamentoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Pagamento from(final String aUuid) {

        return new Pagamento(
                null,
                aUuid != null ? PagamentoUuid.from(aUuid) : null,
                null,
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

        new PagamentoValidator(aHandler, this).validate();
    }

    public PagamentoUuid getUuid() {
        return uuid;
    }
    public PagamentoStatus getStatusCode() {
        return statusCode;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public Long getValor() {
        return valor;
    }
    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
    public String getGatewayTransacaoId() {
        return gatewayTransacaoId;
    }
    public String getGatewayResponse() {
        return gatewayResponse;
    }
    public Instant getDataProcessamento() {
        return dataProcessamento;
    }
    public Instant getDataExpiracao() {
        return dataExpiracao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Pagamento that = (Pagamento) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(pedido, that.pedido) &&
                Objects.equals(valor, that.valor) &&
                statusTransacao == that.statusTransacao &&
                Objects.equals(gatewayTransacaoId, that.gatewayTransacaoId) &&
                Objects.equals(gatewayResponse, that.gatewayResponse) &&
                Objects.equals(dataProcessamento, that.dataProcessamento) &&
                Objects.equals(dataExpiracao, that.dataExpiracao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                pedido,
                valor,
                statusTransacao,
                gatewayTransacaoId,
                gatewayResponse,
                dataProcessamento,
                dataExpiracao);
    }
}
