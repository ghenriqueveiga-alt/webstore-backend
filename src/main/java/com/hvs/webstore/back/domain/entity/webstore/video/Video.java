package com.hvs.webstore.back.domain.entity.webstore.video;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Video extends Entity<VideoId> {

    private final VideoUuid uuid;
    private final VideoStatus statusCode;
    private final String nome;
    private final String caminho;
    private final String extensao;
    private final String tamanho;
    private final String duracao;
    private final String resolucao;
    private final Produto produto;

    private Video(final VideoId id,
                  final VideoUuid uuid,
                  final VideoStatus statusCode,
                  final String nome,
                  final String caminho,
                  final String extensao,
                  final String tamanho,
                  final String duracao,
                  final String resolucao,
                  final Produto produto) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.caminho = caminho;
        this.extensao = extensao;
        this.tamanho = tamanho;
        this.duracao = duracao;
        this.resolucao = resolucao;
        this.produto = produto;
    }

    public static Video create(final String aNome,
                                final String aCaminho,
                                final String aExtensao,
                                final String aTamanho,
                                final String aDuracao,
                                final String aResolucao,
                                final Long aProdutoId) {

        return new Video(
                VideoId.from(-1L),
                VideoUuid.unique(),
                VideoStatus.ACTIVE,
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aDuracao,
                aResolucao,
                aProdutoId != null ? Produto.from(aProdutoId) : null);
    }

    public static Video update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final String aNome,
                                final String aCaminho,
                                final String aExtensao,
                                final String aTamanho,
                                final String aDuracao,
                                final String aResolucao,
                                final Long aProdutoId) {

        return new Video(
                aId != null ? VideoId.from(aId) : null,
                aUuid != null ? VideoUuid.from(aUuid) : null,
                aStatusCode != null ? VideoStatus.findByCode(aStatusCode) : null,
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aDuracao,
                aResolucao,
                aProdutoId != null ? Produto.from(aProdutoId) : null);
    }

    public static Video patch(final String aStatusCode,
                               final String aNome,
                               final String aCaminho,
                               final String aExtensao,
                               final String aTamanho,
                               final String aDuracao,
                               final String aResolucao,
                               final Long aProdutoId,
                               final Video aExisting) {

        return new Video(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? VideoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aCaminho != null ? aCaminho : aExisting.getCaminho(),
                aExtensao != null ? aExtensao : aExisting.getExtensao(),
                aTamanho != null ? aTamanho : aExisting.getTamanho(),
                aDuracao != null ? aDuracao : aExisting.getDuracao(),
                aResolucao != null ? aResolucao : aExisting.getResolucao(),
                aProdutoId != null ? Produto.from(aProdutoId) : aExisting.getProduto());
    }

    public static Video from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final String aNome,
                              final String aCaminho,
                              final String aExtensao,
                              final String aTamanho,
                              final String aDuracao,
                              final String aResolucao,
                              final Produto aProduto) {

        return new Video(
                aId != null ? VideoId.from(aId) : null,
                aUuid != null ? VideoUuid.from(aUuid) : null,
                aStatusDesc != null ? VideoStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aDuracao,
                aResolucao,
                aProduto);
    }

    public static Video from(final Long aId) {

        return new Video(
                aId != null ? VideoId.from(aId) : null,
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

    public static Video from(final String aUuid) {

        return new Video(
                null,
                aUuid != null ? VideoUuid.from(aUuid) : null,
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

        new VideoValidator(aHandler, this).validate();
    }

    public VideoUuid getUuid() {
        return uuid;
    }
    public VideoStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getCaminho() {
        return caminho;
    }
    public String getExtensao() {
        return extensao;
    }
    public String getTamanho() {
        return tamanho;
    }
    public String getDuracao() {
        return duracao;
    }
    public String getResolucao() {
        return resolucao;
    }
    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Video video = (Video) o;

        return Objects.equals(uuid, video.uuid) &&
                statusCode == video.statusCode &&
                Objects.equals(nome, video.nome) &&
                Objects.equals(caminho, video.caminho) &&
                Objects.equals(extensao, video.extensao) &&
                Objects.equals(tamanho, video.tamanho) &&
                Objects.equals(duracao, video.duracao) &&
                Objects.equals(resolucao, video.resolucao) &&
                Objects.equals(produto, video.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                caminho,
                extensao,
                tamanho,
                duracao,
                resolucao,
                produto);
    }
}
