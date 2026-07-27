package com.hvs.webstore.back.domain.entity.webstore.produto;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Produto extends Entity<ProdutoId> {

    private final ProdutoUuid uuid;
    private final ProdutoStatus statusCode;
    private final String nome;
    private final String descricao;
    private final Preco preco;
    private final List<Caracteristica> caracteristicas;
    private final List<Imagem> imagens;
    private final List<Video> videos;
    private final Categoria categoria;
    private final Marca marca;
    private final Instant dataPublicacao;

    private Produto(final ProdutoId id,
                    final ProdutoUuid uuid,
                    final ProdutoStatus statusCode,
                    final String nome,
                    final String descricao,
                    final Preco preco,
                    final List<Caracteristica> caracteristicas,
                    final List<Imagem> imagens,
                    final List<Video> videos,
                    final Categoria categoria,
                    final Marca marca,
                    final Instant dataPublicacao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
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

    public static Produto create(final String aNome,
                                  final String aDescricao,
                                  final Long aPrecoId,
                                  final List<Long> aCaracteristicaIds,
                                  final List<Long> aImagemIds,
                                  final List<Long> aVideoIds,
                                  final Long aCategoriaId,
                                  final Long aMarcaId) {

        final var id = ProdutoId.from(-1L);
        final var uuid = ProdutoUuid.unique();
        final var status = ProdutoStatus.ACTIVE;
        final var preco = aPrecoId != null ? Preco.from(aPrecoId) : null;
        final var caracteristicas = aCaracteristicaIds != null ? aCaracteristicaIds.stream().map(Caracteristica::from).toList() : null;
        final var imagens = aImagemIds != null ? aImagemIds.stream().map(Imagem::from).toList() : null;
        final var videos = aVideoIds != null ? aVideoIds.stream().map(Video::from).toList() : null;
        final var categoria = aCategoriaId != null ? Categoria.from(aCategoriaId) : null;
        final var marca = aMarcaId != null ? Marca.from(aMarcaId) : null;
        final var dataPublicacao = Instant.now();

        return new Produto(
                id,
                uuid,
                status,
                aNome,
                aDescricao,
                preco,
                caracteristicas,
                imagens,
                videos,
                categoria,
                marca,
                dataPublicacao);
    }

    public static Produto update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final String aNome,
                                  final String aDescricao,
                                  final Long aPrecoId,
                                  final List<Long> aCaracteristicaIds,
                                  final List<Long> aImagemIds,
                                  final List<Long> aVideoIds,
                                  final Long aCategoriaId,
                                  final Long aMarcaId,
                                  final Instant aDataPublicacao) {

        final var id = aId != null ? ProdutoId.from(aId) : null;
        final var uuid = aUuid != null ? ProdutoUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? ProdutoStatus.findByCode(aStatusCode) : null;
        final var preco = aPrecoId != null ? Preco.from(aPrecoId) : null;
        final var caracteristicas = aCaracteristicaIds != null ? aCaracteristicaIds.stream().map(Caracteristica::from).toList() : null;
        final var imagens = aImagemIds != null ? aImagemIds.stream().map(Imagem::from).toList() : null;
        final var videos = aVideoIds != null ? aVideoIds.stream().map(Video::from).toList() : null;
        final var categoria = aCategoriaId != null ? Categoria.from(aCategoriaId) : null;
        final var marca = aMarcaId != null ? Marca.from(aMarcaId) : null;

        return new Produto(
                id,
                uuid,
                status,
                aNome,
                aDescricao,
                preco,
                caracteristicas,
                imagens,
                videos,
                categoria,
                marca,
                aDataPublicacao);
    }

    public static Produto patch(final String aStatusCode,
                                 final String aNome,
                                 final String aDescricao,
                                 final Long aPrecoId,
                                 final Long aMarcaId,
                                 final List<Long> aCaracteristicaIds,
                                 final List<Long> aImagemIds,
                                 final List<Long> aVideoIds,
                                 final Long aCategoriaId,
                                 final Produto aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? ProdutoStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var nome = aNome != null ? aNome : aExisting.getNome();
        final var descricao = aDescricao != null ? aDescricao : aExisting.getDescricao();
        final var preco = aPrecoId != null ? Preco.from(aPrecoId) : aExisting.getPreco();
        final var marca = aMarcaId != null ? Marca.from(aMarcaId) : aExisting.getMarca();
        final var caracteristicas = aCaracteristicaIds != null ? aCaracteristicaIds.stream().map(Caracteristica::from).toList() : aExisting.getCaracteristicas();
        final var imagens = aImagemIds != null ? aImagemIds.stream().map(Imagem::from).toList() : aExisting.getImagens();
        final var videos = aVideoIds != null ? aVideoIds.stream().map(Video::from).toList() : aExisting.getVideos();
        final var categoria = aCategoriaId != null ? Categoria.from(aCategoriaId) : aExisting.getCategoria();
        final var dataPublicacao = aExisting.getDataPublicacao();

        return new Produto(
                id,
                uuid,
                status,
                nome,
                descricao,
                preco,
                caracteristicas,
                imagens,
                videos,
                categoria,
                marca,
                dataPublicacao);
    }

    public static Produto from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final String aNome,
                                final String aDescricao,
                                final Preco aPreco,
                                final List<Caracteristica> aCaracteristicas,
                                final List<Imagem> aImagens,
                                final List<Video> aVideos,
                                final Categoria aCategoria,
                                final Marca aMarca,
                                final Instant aDataPublicacao) {

        final var id = aId != null ? ProdutoId.from(aId) : null;
        final var uuid = aUuid != null ? ProdutoUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? ProdutoStatus.findByDesc(aStatusDesc) : null;

        return new Produto(
                id,
                uuid,
                status,
                aNome,
                aDescricao,
                aPreco,
                aCaracteristicas,
                aImagens,
                aVideos,
                aCategoria,
                aMarca,
                aDataPublicacao);
    }

    public static Produto from(final Long aId) {

        return new Produto(
                aId != null ? ProdutoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Produto from(final String aUuid) {

        return new Produto(
                null,
                aUuid != null ? ProdutoUuid.from(aUuid) : null,
                null,
                null,
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
    public void validate(ValidationHandler aHandler) {

        new ProdutoValidator(aHandler, this).validate();
    }

    public ProdutoUuid getUuid() {
        return uuid;
    }
    public ProdutoStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public Preco getPreco() {
        return preco;
    }
    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }
    public List<Imagem> getImagens() {
        return imagens;
    }
    public List<Video> getVideos() {
        return videos;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public Marca getMarca() {
        return marca;
    }
    public Instant getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Produto produto = (Produto) o;

        return Objects.equals(uuid, produto.uuid) &&
                statusCode == produto.statusCode &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(descricao, produto.descricao) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(caracteristicas, produto.caracteristicas) &&
                Objects.equals(imagens, produto.imagens) &&
                Objects.equals(videos, produto.videos) &&
                Objects.equals(categoria, produto.categoria) &&
                Objects.equals(marca, produto.marca) &&
                Objects.equals(dataPublicacao, produto.dataPublicacao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                descricao,
                preco,
                caracteristicas,
                imagens,
                videos,
                categoria,
                marca,
                dataPublicacao);
    }
}
