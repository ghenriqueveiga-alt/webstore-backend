package com.hvs.webstore.back.infra.persistence.television.canal;

import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "canal")
public class CanalEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;
    private String logotipoUrl;
    private String site;

    public CanalEntity() {

    }

    public CanalEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String nome,
                       final String descricao,
                       final String logotipoUrl,
                       final String site) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
        this.logotipoUrl = logotipoUrl;
        this.site = site;
    }

    public static CanalEntity from(final Canal aCanal) {

        return new CanalEntity(
                aCanal.getId().getValue() < 0 ? null : aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                aCanal.getStatus().getDesc(),
                aCanal.getNome(),
                aCanal.getDescricao(),
                aCanal.getLogotipoUrl(),
                aCanal.getSite());
    }

    public static CanalEntity from(final Long aCanalId) {

        final var canal = new CanalEntity();
        canal.setId(aCanalId);

        return canal;
    }

    public Canal toDomain() {

        return Canal.from(getId(),
                          uuid,
                          statusDesc,
                          nome,
                          descricao,
                          logotipoUrl,
                          site);
    }

    public Canal toDomainChildren() {

        return Canal.from(getId(),
                          uuid,
                          statusDesc,
                          nome,
                          descricao,
                          logotipoUrl,
                          site);
    }

    public Canal toDomainSimple() {

        return Canal.from(getId(),
                          uuid,
                          null,
                          null,
                          null,
                          null,
                          null);
    }

    @Override
    public Long getId() {
        return this.id;
    }
    public void setId(final Long aId) {
        this.id = aId;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
