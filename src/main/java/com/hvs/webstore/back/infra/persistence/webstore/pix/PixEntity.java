package com.hvs.webstore.back.infra.persistence.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "pix")
public class PixEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String chavePix;
    private String tipoChavePixDesc;

    public PixEntity() {

    }

    public PixEntity(final Long id,
                     final String uuid,
                     final String statusDesc,
                     final String chavePix,
                     final String tipoChavePixDesc) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.chavePix = chavePix;
        this.tipoChavePixDesc = tipoChavePixDesc;
    }

    public static PixEntity from(Pix aPix) {

        return new PixEntity(
                aPix.getId().getValue() < 0 ? null : aPix.getId().getValue(),
                aPix.getUuid().getValue(),
                aPix.getStatusCode().getDesc(),
                aPix.getChavePix(),
                aPix.getTipoChavePix().getDesc()
        );
    }

    public static PixEntity from(final Long aPixId) {

        final var pix = new PixEntity();
        pix.setId(aPixId);

        return pix;
    }

    public Pix toDomain() {

        return Pix.from(
                getId(),
                uuid,
                statusDesc,
                chavePix,
                tipoChavePixDesc
        );
    }

    public Pix toDomainChildren() {

        return Pix.from(
                getId(),
                uuid,
                statusDesc,
                chavePix,
                tipoChavePixDesc
        );
    }

    public Pix toDomainSimple() {

        return Pix.from(
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
