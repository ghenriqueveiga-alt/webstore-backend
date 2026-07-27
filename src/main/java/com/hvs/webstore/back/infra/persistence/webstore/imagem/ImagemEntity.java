package com.hvs.webstore.back.infra.persistence.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "imagem")
public class ImagemEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String caminho;
    private String extensao;
    private String tamanho;
    private String resolucao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    public ImagemEntity() {

    }

    public ImagemEntity(final Long id,
                        final String uuid,
                        final String statusDesc,
                        final String nome,
                        final String caminho,
                        final String extensao,
                        final String tamanho,
                        final String resolucao,
                        final ProdutoEntity produto) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.caminho = caminho;
        this.extensao = extensao;
        this.tamanho = tamanho;
        this.resolucao = resolucao;
        this.produto = produto;
    }

    public static ImagemEntity from(final Imagem aImagem) {

        return new ImagemEntity(
                aImagem.getId().getValue() < 0 ? null : aImagem.getId().getValue(),
                aImagem.getUuid().getValue(),
                aImagem.getStatusCode().getDesc(),
                aImagem.getNome(),
                aImagem.getCaminho(),
                aImagem.getExtensao(),
                aImagem.getTamanho(),
                aImagem.getResolucao(),
                aImagem.getProduto() != null ? ProdutoEntity.from(aImagem.getProduto()) : null
        );
    }

    public static ImagemEntity from(final Long aImagemID) {

        final var imagem = new ImagemEntity();
        imagem.setId(aImagemID);

        return imagem;
    }

    public Imagem toDomain() {

        return Imagem.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                caminho,
                extensao,
                tamanho,
                resolucao,
                produto.toDomainChildren()
        );
    }

    public Imagem toDomainChildren() {

        return Imagem.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                caminho,
                extensao,
                tamanho,
                resolucao,
                produto.toDomainSimple()
        );
    }

    public Imagem toDomainSimple() {

        return Imagem.from(
                getId(),
                uuid,
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
