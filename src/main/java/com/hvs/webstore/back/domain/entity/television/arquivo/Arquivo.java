package com.hvs.webstore.back.domain.entity.television.arquivo;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Arquivo extends Entity<ArquivoId> {

    private final ArquivoUuid uuid;
    private final ArquivoStatus status;
    private final String nome;
    private final String tipoArquivo;
    private final Long tamanho;
    private final String caminho;

    private Arquivo(final ArquivoId id,
                    final ArquivoUuid uuid,
                    final ArquivoStatus status,
                    final String nome,
                    final String tipoArquivo,
                    final Long tamanho,
                    final String caminho) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.nome = nome;
        this.tipoArquivo = tipoArquivo;
        this.tamanho = tamanho;
        this.caminho = caminho;
    }

    public static Arquivo create(final String aNome,
                                 final String aTipoArquivo,
                                 final Long aTamanho,
                                 final String aCaminho) {

        return new Arquivo(
                ArquivoId.from(-1L),
                ArquivoUuid.unique(),
                ArquivoStatus.ACTIVE,
                aNome,
                aTipoArquivo,
                aTamanho,
                aCaminho);
    }

    public static Arquivo update(final Long aId,
                                 final String aUuid,
                                 final String aStatusCode,
                                 final String aNome,
                                 final String aTipo,
                                 final Long aTamanho,
                                 final String aCaminho) {

        return new Arquivo(
                aId != null ? ArquivoId.from(aId) : null,
                aUuid != null ? ArquivoUuid.from(aUuid) : null,
                aStatusCode != null ? ArquivoStatus.findByCode(aStatusCode) : null,
                aNome,
                aTipo,
                aTamanho,
                aCaminho);
    }

    public static Arquivo patch(final String aStatusCode,
                                final String aNome,
                                final String aTipoArquivo,
                                final Long aTamanho,
                                final String aCaminho,
                                final Arquivo aExisting) {

        return new Arquivo(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? ArquivoStatus.findByCode(aStatusCode) : aExisting.getStatus(),
                aNome != null ? aNome : aExisting.getNome(),
                aTipoArquivo != null ? aTipoArquivo : aExisting.getTipo(),
                aTamanho != null ? aTamanho : aExisting.getTamanho(),
                aCaminho != null ? aCaminho : aExisting.getCaminho());
    }

    public static Arquivo from(final Long aId,
                               final String aUuid,
                               final String aStatusDesc,
                               final String aNome,
                               final String aTipo,
                               final Long aTamanho,
                               final String aCaminho) {

        return new Arquivo(
                aId != null ? ArquivoId.from(aId) : null,
                aUuid != null ? ArquivoUuid.from(aUuid) : null,
                aStatusDesc != null ? ArquivoStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aTipo,
                aTamanho,
                aCaminho);
    }

    public static Arquivo from(final Long aId) {

        return new Arquivo(
                aId != null ? ArquivoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Arquivo from(final String aUuid) {

        return new Arquivo(
                null,
                aUuid != null ? ArquivoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new ArquivoValidator(aHandler, this).validate();
    }

    public ArquivoUuid getUuid() {
        return uuid;
    }
    public ArquivoStatus getStatus() {
        return status;
    }
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipoArquivo;
    }
    public Long getTamanho() {
        return tamanho;
    }
    public String getCaminho() {
        return caminho;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Arquivo arquivo = (Arquivo) o;

        return Objects.equals(uuid, arquivo.uuid) &&
                status == arquivo.status &&
                Objects.equals(nome, arquivo.nome) &&
                Objects.equals(tipoArquivo, arquivo.tipoArquivo) &&
                Objects.equals(tamanho, arquivo.tamanho) &&
                Objects.equals(caminho, arquivo.caminho);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                nome,
                tipoArquivo,
                tamanho,
                caminho);
    }
}
