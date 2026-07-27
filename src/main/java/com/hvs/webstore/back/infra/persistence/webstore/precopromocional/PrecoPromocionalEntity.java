package com.hvs.webstore.back.infra.persistence.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "preco_promocional")
public class PrecoPromocionalEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "preco_promocional_id")
    private PrecoEntity precoPromocional;
    private Instant dataInicio;
    private Instant dataFim;

    public PrecoPromocionalEntity() {

    }

    public PrecoPromocionalEntity(final Long id,
                                  final String uuid,
                                  final String statusDesc,
                                  final ProdutoEntity produto,
                                  final PrecoEntity precoPromocional,
                                  final Instant dataInicio,
                                  final Instant dataFim) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.produto = produto;
        this.precoPromocional = precoPromocional;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public static PrecoPromocionalEntity from(PrecoPromocional aPrecoPromocional) {

        return new PrecoPromocionalEntity(
                aPrecoPromocional.getId().getValue() < 0 ? null : aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                aPrecoPromocional.getStatusCode().getDesc(),
                aPrecoPromocional.getProduto() != null ? ProdutoEntity.from(aPrecoPromocional.getProduto().getId().getValue()) : null,
                aPrecoPromocional.getPrecoPromocional() != null ? PrecoEntity.from(aPrecoPromocional.getPrecoPromocional().getId().getValue()) : null,
                aPrecoPromocional.getDataInicio(),
                aPrecoPromocional.getDataFim()
        );
    }

    public static PrecoPromocionalEntity from(final Long aPrecoPromocionalId) {

        final var precoPromocional = new PrecoPromocionalEntity();
        precoPromocional.setId(aPrecoPromocionalId);

        return precoPromocional;
    }

    public PrecoPromocional toDomain() {

        return PrecoPromocional.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainChildren() : null,
                precoPromocional != null ? precoPromocional.toDomainChildren() : null,
                dataInicio,
                dataFim
        );
    }

    public PrecoPromocional toDomainChildren() {

        return PrecoPromocional.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainSimple() : null,
                precoPromocional != null ? precoPromocional.toDomainSimple() : null,
                dataInicio,
                dataFim
        );
    }

    public PrecoPromocional toDomainSimple() {

        return PrecoPromocional.from(
                getId(),
                uuid,
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
