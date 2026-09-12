package com.hvs.webstore.back.infra.persistence.television.arquivo;

import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "arquivo")
public class ArquivoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String tipo;
    private Long tamanho;
    private String caminho;

    public ArquivoEntity() {

    }

    public ArquivoEntity(final Long id,
                         final String uuid,
                         final String statusDesc,
                         final String nome,
                         final String tipo,
                         final Long tamanho,
                         final String caminho) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.caminho = caminho;
    }

    public static ArquivoEntity from(final Arquivo aArquivo) {

        return new ArquivoEntity(
                aArquivo.getId().getValue() < 0 ? null : aArquivo.getId().getValue(),
                aArquivo.getUuid().getValue(),
                aArquivo.getStatus() != null ? aArquivo.getStatus().getDesc() : null,
                aArquivo.getNome(),
                aArquivo.getTipo(),
                aArquivo.getTamanho(),
                aArquivo.getCaminho());
    }

    public static ArquivoEntity from(final Long aArquivoId) {

        final var arquivo = new ArquivoEntity();
        arquivo.setId(aArquivoId);

        return arquivo;
    }

    public Arquivo toDomain() {

        return Arquivo.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                tipo,
                tamanho,
                caminho);
    }

    public Arquivo toDomainChildren() {

        return Arquivo.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                tipo,
                tamanho,
                caminho);
    }

    public Arquivo toDomainSimple() {

        return Arquivo.from(
                getId(),
                uuid,
                null,
                nome,
                null,
                null,
                null);
    }

    @Override
    public Long getId() {
        return this.id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
