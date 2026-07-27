package com.hvs.webstore.back.infra.persistence.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "anexo")
public class AnexoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String entidade;
    private Long entidadeId;
    private String nome;
    private String tipo;
    private Long tamanho;
    private String url;
    private Instant dataUpload;

    public AnexoEntity() {

    }

    public AnexoEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String entidade,
                       final Long entidadeId,
                       final String nome,
                       final String tipo,
                       final Long tamanho,
                       final String url,
                       final Instant dataUpload) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.url = url;
        this.dataUpload = dataUpload;
    }

    public static AnexoEntity from(final Anexo aAnexo) {

        return new AnexoEntity(
                aAnexo.getId().getValue() < 0 ? null : aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                aAnexo.getStatusCode().getDesc(),
                aAnexo.getEntidadeNome(),
                aAnexo.getEntidadeId(),
                aAnexo.getNome(),
                aAnexo.getTipo(),
                aAnexo.getTamanho(),
                aAnexo.getUrl(),
                aAnexo.getDataUpload()
        );
    }

    public static AnexoEntity from(final Long aAnexoId) {

        final var anexo = new AnexoEntity();
        anexo.setId(aAnexoId);

        return anexo;
    }

    public Anexo toDomain() {

        return Anexo.from(
                getId(),
                uuid,
                statusDesc,
                entidade,
                entidadeId,
                nome,
                tipo,
                tamanho,
                url,
                dataUpload
        );
    }

    public Anexo toDomainChildren() {

        return Anexo.from(
                getId(),
                uuid,
                statusDesc,
                entidade,
                entidadeId,
                nome,
                tipo,
                tamanho,
                url,
                dataUpload
        );
    }

    public Anexo toDomainSimple() {

        return Anexo.from(
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
