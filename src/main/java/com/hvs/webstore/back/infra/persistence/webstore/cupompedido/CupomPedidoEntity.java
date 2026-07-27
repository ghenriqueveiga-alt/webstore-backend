package com.hvs.webstore.back.infra.persistence.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.cupom.CupomEntity;
import com.hvs.webstore.back.infra.persistence.webstore.pedido.PedidoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cupom_pedido")
public class CupomPedidoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private CupomEntity cupom;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "preco_id")
    private PrecoEntity valorDesconto;

    public CupomPedidoEntity() {

    }

    public CupomPedidoEntity(final Long id,
                             final String uuid,
                             final String statusDesc,
                             final CupomEntity cupom,
                             final PedidoEntity pedido,
                             final PrecoEntity valorDesconto) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.cupom = cupom;
        this.pedido = pedido;
        this.valorDesconto = valorDesconto;
    }

    public static CupomPedidoEntity from(final CupomPedido aCupomPedido) {

        return new CupomPedidoEntity(
                aCupomPedido.getId().getValue() < 0 ? null : aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                aCupomPedido.getStatusCode().getDesc(),
                aCupomPedido.getCupom() != null ? CupomEntity.from(aCupomPedido.getCupom().getId().getValue()) : null,
                aCupomPedido.getPedido() != null ? PedidoEntity.from(aCupomPedido.getPedido().getId().getValue()) : null,
                aCupomPedido.getValorDesconto() != null ? PrecoEntity.from(aCupomPedido.getValorDesconto().getId().getValue()) : null
        );
    }

    public static CupomPedidoEntity from(final Long aCupomPedidoId) {

        final var cupomPedido = new CupomPedidoEntity();
        cupomPedido.setId(aCupomPedidoId);

        return cupomPedido;
    }

    public CupomPedido toDomain() {

        return CupomPedido.from(
                getId(),
                uuid,
                statusDesc,
                cupom != null ? cupom.toDomainChildren() : null,
                pedido != null ? pedido.toDomainChildren() : null,
                valorDesconto != null ? valorDesconto.toDomainChildren() : null
        );
    }

    public CupomPedido toDomainChildren() {

        return CupomPedido.from(
                getId(),
                uuid,
                statusDesc,
                cupom != null ? cupom.toDomainSimple() : null,
                pedido != null ? pedido.toDomainSimple() : null,
                valorDesconto != null ? valorDesconto.toDomainSimple() : null
        );
    }

    public CupomPedido toDomainSimple() {

        return CupomPedido.from(
                getId(),
                uuid,
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
