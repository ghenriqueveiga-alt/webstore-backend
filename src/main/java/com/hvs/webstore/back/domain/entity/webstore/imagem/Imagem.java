package com.hvs.webstore.back.domain.entity.webstore.imagem;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Imagem extends Entity<ImagemId> {

    private final ImagemUuid uuid;
    private final ImagemStatus statusCode;
    private final String nome;
    private final String caminho;
    private final String extensao;
    private final String tamanho;
    private final String resolucao;
    private final Produto produto;

    private Imagem(final ImagemId id,
                   final ImagemUuid uuid,
                   final ImagemStatus statusCode,
                   final String nome,
                   final String caminho,
                   final String extensao,
                   final String tamanho,
                   final String resolucao,
                   final Produto produto) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.caminho = caminho;
        this.extensao = extensao;
        this.tamanho = tamanho;
        this.resolucao = resolucao;
        this.produto = produto;
    }

    public static Imagem create(final String aNome,
                                final String aCaminho,
                                final String aExtensao,
                                final String aTamanho,
                                final String aResolucao,
                                final Long aProdutoId) {

        return new Imagem(
                ImagemId.from(-1L),
                ImagemUuid.unique(),
                ImagemStatus.ACTIVE,
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aResolucao,
                aProdutoId != null ? Produto.from(aProdutoId) : null);
    }

    public static Imagem update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final String aNome,
                                final String aCaminho,
                                final String aExtensao,
                                final String aTamanho,
                                final String aResolucao,
                                final Long aProdutoId) {

        return new Imagem(
                aId != null ? ImagemId.from(aId) : null,
                aUuid != null ? ImagemUuid.from(aUuid) : null,
                aStatusCode != null ? ImagemStatus.findByCode(aStatusCode) : null,
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aResolucao,
                aProdutoId != null ? Produto.from(aProdutoId) : null);
    }

    public static Imagem patch(final String aStatusCode,
                               final String aNome,
                               final String aCaminho,
                               final String aExtensao,
                               final String aTamanho,
                               final String aResolucao,
                               final Long aProdutoId,
                               final Imagem aExisting) {

        return new Imagem(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? ImagemStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aCaminho != null ? aCaminho : aExisting.getCaminho(),
                aExtensao != null ? aExtensao : aExisting.getExtensao(),
                aTamanho != null ? aTamanho : aExisting.getTamanho(),
                aResolucao != null ? aResolucao : aExisting.getResolucao(),
                aProdutoId != null ? Produto.from(aProdutoId) : aExisting.getProduto());
    }

    public static Imagem from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final String aNome,
                              final String aCaminho,
                              final String aExtensao,
                              final String aTamanho,
                              final String aResolucao,
                              final Produto aProduto) {

        return new Imagem(
                aId != null ? ImagemId.from(aId) : null,
                aUuid != null ? ImagemUuid.from(aUuid) : null,
                aStatusDesc != null ? ImagemStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aCaminho,
                aExtensao,
                aTamanho,
                aResolucao,
                aProduto);
    }

    public static Imagem from(final Long aId) {

        return new Imagem(
                aId != null ? ImagemId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Imagem from(final String aUuid) {

        return new Imagem(
                null,
                aUuid != null ? ImagemUuid.from(aUuid) : null,
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

        new ImagemValidator(aHandler, this).validate();
    }

    public ImagemUuid getUuid() {
        return uuid;
    }
    public ImagemStatus getStatusCode() {
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

        Imagem that = (Imagem) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(caminho, that.caminho) &&
                Objects.equals(extensao, that.extensao) &&
                Objects.equals(tamanho, that.tamanho) &&
                Objects.equals(resolucao, that.resolucao) &&
                Objects.equals(produto, that.produto);
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
                resolucao,
                produto);
    }
}
