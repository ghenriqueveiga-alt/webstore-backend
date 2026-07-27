package com.hvs.webstore.back.infra.persistence.webstore.pagamento;

import com.hvs.webstore.back.domain.entity.webstore.pagamento.Pagamento;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.pedido.PedidoEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "pagamento")
public class PagamentoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
    private Long valor;
    private String statusTransacaoDesc;
    private String gatewayTransacaoId;
    private String gatewayResponse;
    private Instant dataProcessamento;
    private Instant dataExpiracao;

    public PagamentoEntity() {

    }

    public PagamentoEntity(final Long id,
                           final String uuid,
                           final String statusDesc,
                           final PedidoEntity pedido,
                           final Long valor,
                           final String statusTransacaoDesc,
                           final String gatewayTransacaoId,
                           final String gatewayResponse,
                           final Instant dataProcessamento,
                           final Instant dataExpiracao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.pedido = pedido;
        this.valor = valor;
        this.statusTransacaoDesc = statusTransacaoDesc;
        this.gatewayTransacaoId = gatewayTransacaoId;
        this.gatewayResponse = gatewayResponse;
        this.dataProcessamento = dataProcessamento;
        this.dataExpiracao = dataExpiracao;
    }

    public static PagamentoEntity from(Pagamento aPagamento) {

        return new PagamentoEntity(
                aPagamento.getId().getValue() < 0 ? null : aPagamento.getId().getValue(),
                aPagamento.getUuid().getValue(),
                aPagamento.getStatusCode().getDesc(),
                aPagamento.getPedido() != null ? PedidoEntity.from(aPagamento.getPedido().getId().getValue()) : null,
                aPagamento.getValor(),
                aPagamento.getStatusTransacao().getDesc(),
                aPagamento.getGatewayTransacaoId(),
                aPagamento.getGatewayResponse(),
                aPagamento.getDataProcessamento(),
                aPagamento.getDataExpiracao()
        );
    }

    public static PagamentoEntity from(final Long aPagamentoId) {

        final var pagamento = new PagamentoEntity();
        pagamento.setId(aPagamentoId);

        return pagamento;
    }

    public Pagamento toDomain() {

        return Pagamento.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainChildren() : null,
                valor,
                statusTransacaoDesc,
                gatewayTransacaoId,
                gatewayResponse,
                dataProcessamento,
                dataExpiracao
        );
    }

    public Pagamento toDomainChildren() {

        return Pagamento.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainSimple() : null,
                valor,
                statusTransacaoDesc,
                gatewayTransacaoId,
                gatewayResponse,
                dataProcessamento,
                dataExpiracao
        );
    }

    public Pagamento toDomainSimple() {

        return Pagamento.from(
                getId(),
                uuid,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
