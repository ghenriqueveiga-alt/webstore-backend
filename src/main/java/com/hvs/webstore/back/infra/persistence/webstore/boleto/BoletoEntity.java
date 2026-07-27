package com.hvs.webstore.back.infra.persistence.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "boleto")
public class BoletoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String codigoBarras;
    private LocalDate vencimento;

    public BoletoEntity() {

    }

    public BoletoEntity(final Long id,
                        final String uuid,
                        final String statusDesc,
                        final String codigoBarras,
                        final LocalDate vencimento) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.codigoBarras = codigoBarras;
        this.vencimento = vencimento;
    }

    public static BoletoEntity from(final Boleto aBoleto) {

        return new BoletoEntity(
                aBoleto.getId().getValue() < 0 ? null : aBoleto.getId().getValue(),
                aBoleto.getUuid().getValue(),
                aBoleto.getStatusCode().getDesc(),
                aBoleto.getCodigoBarras(),
                aBoleto.getVencimento() != null ? LocalDate.parse(aBoleto.getVencimento()) : null
        );
    }

    public static BoletoEntity from(final Long aBoletoId) {

        final var boleto = new BoletoEntity();
        boleto.setId(aBoletoId);

        return boleto;
    }

    public Boleto toDomain() {

        return Boleto.from(
                getId(),
                uuid,
                statusDesc,
                codigoBarras,
                vencimento != null ? vencimento.toString() : null
        );
    }

    public Boleto toDomainChildren() {

        return Boleto.from(
                getId(),
                uuid,
                statusDesc,
                codigoBarras,
                vencimento != null ? vencimento.toString() : null
        );
    }

    public Boleto toDomainSimple() {

        return Boleto.from(
                getId(),
                uuid,
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
