package com.hvs.webstore.back.infra.persistence.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "video")
public class VideoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String caminho;
    private String extensao;
    private String tamanho;
    private String duracao;
    private String resolucao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    public VideoEntity() {

    }

    public VideoEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String nome,
                       final String caminho,
                       final String extensao,
                       final String tamanho,
                       final String duracao,
                       final String resolucao,
                       final ProdutoEntity produto) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.caminho = caminho;
        this.extensao = extensao;
        this.tamanho = tamanho;
        this.duracao = duracao;
        this.resolucao = resolucao;
        this.produto = produto;
    }

    public static VideoEntity from(final Video aVideo) {

        return new VideoEntity(
                aVideo.getId().getValue() < 0 ? null : aVideo.getId().getValue(),
                aVideo.getUuid().getValue(),
                aVideo.getStatusCode().getDesc(),
                aVideo.getNome(),
                aVideo.getCaminho(),
                aVideo.getExtensao(),
                aVideo.getTamanho(),
                aVideo.getDuracao(),
                aVideo.getResolucao(),
                aVideo.getProduto() != null ? ProdutoEntity.from(aVideo.getProduto().getId().getValue()) : null
        );
    }

    public static VideoEntity from(final Long aVideoID) {

        final var video = new VideoEntity();
        video.setId(aVideoID);

        return video;
    }

    public Video toDomain() {

        return Video.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                caminho,
                extensao,
                tamanho,
                duracao,
                resolucao,
                produto != null ? produto.toDomainChildren() : null
        );
    }

    public Video toDomainChildren() {

        return Video.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                caminho,
                extensao,
                tamanho,
                duracao,
                resolucao,
                produto != null ? produto.toDomainSimple() : null
        );
    }

    public Video toDomainSimple() {

        return Video.from(
                getId(),
                uuid,
                null,
                nome,
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
