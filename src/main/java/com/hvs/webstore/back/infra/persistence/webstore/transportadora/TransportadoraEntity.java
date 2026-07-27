package com.hvs.webstore.back.infra.persistence.webstore.transportadora;

import com.hvs.webstore.back.domain.entity.webstore.transportadora.Transportadora;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "transportadora")
public class TransportadoraEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;

    public TransportadoraEntity() {

    }

    public TransportadoraEntity(final Long id,
                                final String uuid,
                                final String statusDesc,
                                final String nome) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
    }

    public static TransportadoraEntity from(Transportadora aTransportadora) {

        return new TransportadoraEntity(
                aTransportadora.getId().getValue() < 0 ? null : aTransportadora.getId().getValue(),
                aTransportadora.getUuid().getValue(),
                aTransportadora.getStatusCode().getDesc(),
                aTransportadora.getNome()
        );
    }

    public static TransportadoraEntity from(final Long aTransportadoraId) {

        final var transportadora = new TransportadoraEntity();
        transportadora.setId(aTransportadoraId);

        return transportadora;
    }

    public Transportadora toDomain() {

        return Transportadora.from(
                getId(),
                uuid,
                statusDesc,
                nome
        );
    }

    public Transportadora toDomainChildren() {

        return Transportadora.from(
                getId(),
                uuid,
                statusDesc,
                nome
        );
    }

    public Transportadora toDomainSimple() {

        return Transportadora.from(
                getId(),
                uuid,
                null,
                nome
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
