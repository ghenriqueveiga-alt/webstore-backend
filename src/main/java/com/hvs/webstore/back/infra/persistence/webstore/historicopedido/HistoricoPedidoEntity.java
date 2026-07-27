package com.hvs.webstore.back.infra.persistence.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.pedido.PedidoEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "historico_pedido")
public class HistoricoPedidoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
    private String statusAnteriorDesc;
    private String statusNovoDesc;
    private String observacao;
    private String criadoPor;
    private Instant dataCriacao;

    public HistoricoPedidoEntity() {

    }

    public HistoricoPedidoEntity(final Long id,
                                 final String uuid,
                                 final String statusDesc,
                                 final PedidoEntity pedido,
                                 final String statusAnteriorDesc,
                                 final String statusNovoDesc,
                                 final String observacao,
                                 final String criadoPor,
                                 final Instant dataCriacao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.pedido = pedido;
        this.statusAnteriorDesc = statusAnteriorDesc;
        this.statusNovoDesc = statusNovoDesc;
        this.observacao = observacao;
        this.criadoPor = criadoPor;
        this.dataCriacao = dataCriacao;
    }

    public static HistoricoPedidoEntity from(final HistoricoPedido aHistoricoPedido) {

        return new HistoricoPedidoEntity(
                aHistoricoPedido.getId().getValue() < 0 ? null : aHistoricoPedido.getId().getValue(),
                aHistoricoPedido.getUuid().getValue(),
                aHistoricoPedido.getStatusCode().getDesc(),
                aHistoricoPedido.getPedido() != null ? PedidoEntity.from(aHistoricoPedido.getPedido()) : null,
                aHistoricoPedido.getStatusAnterior(),
                aHistoricoPedido.getStatusNovo(),
                aHistoricoPedido.getObservacao(),
                aHistoricoPedido.getCriadoPor(),
                aHistoricoPedido.getDataCriacao()
        );
    }

    public static HistoricoPedidoEntity from(final Long aHistoricoPedidoId) {

        final var historicoPedido = new HistoricoPedidoEntity();
        historicoPedido.setId(aHistoricoPedidoId);

        return historicoPedido;
    }

    public HistoricoPedido toDomain() {

        return HistoricoPedido.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainChildren() : null,
                statusAnteriorDesc,
                statusNovoDesc,
                observacao,
                criadoPor,
                dataCriacao
        );
    }

    public HistoricoPedido toDomainChildren() {

        return HistoricoPedido.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainSimple() : null,
                statusAnteriorDesc,
                statusNovoDesc,
                observacao,
                criadoPor,
                dataCriacao
        );
    }

    public HistoricoPedido toDomainSimple() {

        return HistoricoPedido.from(
                getId(),
                uuid,
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
