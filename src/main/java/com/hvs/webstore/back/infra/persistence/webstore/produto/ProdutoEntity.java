package com.hvs.webstore.back.infra.persistence.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.caracteristica.CaracteristicaEntity;
import com.hvs.webstore.back.infra.persistence.webstore.categoria.CategoriaEntity;
import com.hvs.webstore.back.infra.persistence.webstore.imagem.ImagemEntity;
import com.hvs.webstore.back.infra.persistence.webstore.marca.MarcaEntity;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.video.VideoEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "produto")
public class ProdutoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preco_id", referencedColumnName = "id")
    private PrecoEntity preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaracteristicaEntity> caracteristicas;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagemEntity> imagens;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoEntity> videos;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private MarcaEntity marca;
    private Instant dataPublicacao;

    public ProdutoEntity() {

    }

    public ProdutoEntity(final Long id,
                         final String uuid,
                         final String statusDesc,
                         final String nome,
                         final String descricao,
                         final PrecoEntity preco,
                         final List<CaracteristicaEntity> caracteristicas,
                         final List<ImagemEntity> imagens,
                         final List<VideoEntity> videos,
                         final CategoriaEntity categoria,
                         final MarcaEntity marca,
                         final Instant dataPublicacao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.caracteristicas = caracteristicas;
        this.imagens = imagens;
        this.videos = videos;
        this.categoria = categoria;
        this.marca = marca;
        this.dataPublicacao = dataPublicacao;
    }

    public static ProdutoEntity from(final Produto aProduto) {

        return new ProdutoEntity(
                aProduto.getId().getValue() < 0 ? null : aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                aProduto.getStatusCode().getDesc(),
                aProduto.getNome(),
                aProduto.getDescricao(),
                aProduto.getPreco() != null ? PrecoEntity.from(aProduto.getPreco()) : null,
                aProduto.getCaracteristicas() != null && !aProduto.getCaracteristicas().isEmpty() ?
                        aProduto.getCaracteristicas().stream().map(caracteristica ->
                                CaracteristicaEntity.from(caracteristica.getId().getValue())).toList() : null,
                aProduto.getImagens() != null && !aProduto.getImagens().isEmpty() ?
                        aProduto.getImagens().stream().map(imagem ->
                                ImagemEntity.from(imagem.getId().getValue())).toList() : null,
                aProduto.getVideos() != null && !aProduto.getVideos().isEmpty() ?
                        aProduto.getVideos().stream().map(video ->
                                VideoEntity.from(video.getId().getValue())).toList() : null,
                aProduto.getCategoria() != null ? CategoriaEntity.from(aProduto.getCategoria().getId().getValue()) : null,
                aProduto.getMarca() != null ? MarcaEntity.from(aProduto.getMarca().getId().getValue()) : null,
                aProduto.getDataPublicacao()
        );
    }

    public static ProdutoEntity from(final Long aProdutoID) {

        final var produto = new ProdutoEntity();
        produto.setId(aProdutoID);

        return produto;
    }

    public Produto toDomain() {

        return Produto.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                preco != null ? preco.toDomainChildren() : null,
                caracteristicas != null && !caracteristicas.isEmpty() ?
                        caracteristicas.stream().map(CaracteristicaEntity::toDomainChildren).toList() : null,
                imagens != null && !imagens.isEmpty() ?
                        imagens.stream().map(ImagemEntity::toDomainChildren).toList() : null,
                videos != null && !videos.isEmpty() ?
                        videos.stream().map(VideoEntity::toDomainChildren).toList() : null,
                categoria != null ? categoria.toDomainChildren() : null,
                marca != null ? marca.toDomainChildren() : null,
                dataPublicacao
        );
    }

    public Produto toDomainChildren() {

        return Produto.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                preco != null ? preco.toDomainSimple() : null,
                caracteristicas != null && !caracteristicas.isEmpty() ?
                        caracteristicas.stream().map(CaracteristicaEntity::toDomainSimple).toList() : null,
                imagens != null && !imagens.isEmpty() ?
                        imagens.stream().map(ImagemEntity::toDomainSimple).toList() : null,
                videos != null && !videos.isEmpty() ?
                        videos.stream().map(VideoEntity::toDomainSimple).toList() : null,
                categoria != null ? categoria.toDomainSimple() : null,
                marca != null ? marca.toDomainSimple() : null,
                dataPublicacao
        );
    }

    public Produto toDomainSimple() {

        return Produto.from(
                getId(),
                uuid,
                null,
                nome,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
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
