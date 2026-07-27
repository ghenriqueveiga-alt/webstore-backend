package com.hvs.webstore.back.infra.persistence.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.MovimentoEstoque;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoqueEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private EstoqueEntity estoque;
    private String tipoCode;
    private Integer quantidade;
    private String observacao;
    private Instant data;

    public MovimentoEstoqueEntity() {

    }

    public MovimentoEstoqueEntity(final Long id,
                                  final String uuid,
                                  final String statusDesc,
                                  final EstoqueEntity estoque,
                                  final String tipoCode,
                                  final Integer quantidade,
                                  final String observacao,
                                  final Instant data) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.estoque = estoque;
        this.tipoCode = tipoCode;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.data = data;
    }

    public static MovimentoEstoqueEntity from(final MovimentoEstoque aMovimento) {

        return new MovimentoEstoqueEntity(
                aMovimento.getId().getValue() < 0 ? null : aMovimento.getId().getValue(),
                aMovimento.getUuid().getValue(),
                aMovimento.getStatusCode().getDesc(),
                aMovimento.getEstoque() != null ? EstoqueEntity.from(aMovimento.getEstoque().getId().getValue()) : null,
                aMovimento.getTipo().getCode(),
                aMovimento.getQuantidade(),
                aMovimento.getObservacao(),
                aMovimento.getData()
        );
    }

    public static MovimentoEstoqueEntity from(final Long aMovimentoId) {

        final var movimento = new MovimentoEstoqueEntity();
        movimento.setId(aMovimentoId);

        return movimento;
    }

    public MovimentoEstoque toDomain() {

        return MovimentoEstoque.from(
                getId(),
                uuid,
                statusDesc,
                estoque != null ? estoque.toDomainChildren() : null,
                tipoCode,
                quantidade,
                observacao,
                data
        );
    }

    public MovimentoEstoque toDomainChildren() {

        return MovimentoEstoque.from(
                getId(),
                uuid,
                statusDesc,
                estoque != null ? estoque.toDomainSimple() : null,
                tipoCode,
                quantidade,
                observacao,
                data
        );
    }

    public MovimentoEstoque toDomainSimple() {

        return MovimentoEstoque.from(
                getId(),
                uuid,
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
