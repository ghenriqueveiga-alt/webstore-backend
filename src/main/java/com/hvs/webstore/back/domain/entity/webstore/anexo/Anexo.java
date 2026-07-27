package com.hvs.webstore.back.domain.entity.webstore.anexo;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;


import java.time.Instant;
import java.util.Objects;

public class Anexo extends Entity<AnexoId> {

    private final AnexoUuid uuid;
    private final AnexoStatus statusCode;
    private final String entidadeNome;
    private final Long entidadeId;
    private final String nome;
    private final String tipo;
    private final Long tamanho;
    private final String url;
    private final Instant dataUpload;

    private Anexo(final AnexoId id,
                  final AnexoUuid uuid,
                  final AnexoStatus statusCode,
                  final String entidadeNome,
                  final Long entidadeId,
                  final String nome,
                  final String tipo,
                  final Long tamanho,
                  final String url,
                  final Instant dataUpload) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.entidadeNome = entidadeNome;
        this.entidadeId = entidadeId;
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.url = url;
        this.dataUpload = dataUpload;
    }

    public static Anexo create(final String aEntidadeNome,
                               final Long aEntidadeId,
                               final String aNome,
                               final String aTipo,
                               final Long aTamanho,
                               final String aUrl) {

        return new Anexo(
                AnexoId.from(-1L),
                AnexoUuid.unique(),
                AnexoStatus.ACTIVE,
                aEntidadeNome,
                aEntidadeId,
                aNome,
                aTipo,
                aTamanho,
                aUrl,
                Instant.now());
    }

    public static Anexo update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final String aEntidadeNome,
                               final Long aEntidadeId,
                               final String aNome,
                               final String aTipo,
                               final Long aTamanho,
                               final String aUrl,
                               final Instant aDataUpload) {

        return new Anexo(
                aId != null ? AnexoId.from(aId) : null,
                aUuid != null ? AnexoUuid.from(aUuid) : null,
                aStatusCode != null ? AnexoStatus.findByCode(aStatusCode) : null,
                aEntidadeNome,
                aEntidadeId,
                aNome,
                aTipo,
                aTamanho,
                aUrl,
                aDataUpload);
    }

    public static Anexo patch(final String aStatusCode,
                              final String aEntidadeNome,
                              final Long aEntidadeId,
                              final String aNome,
                              final String aTipo,
                              final Long aTamanho,
                              final String aUrl,
                              final Anexo aExisting) {

        return new Anexo(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? AnexoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aEntidadeNome != null ? aEntidadeNome : aExisting.getEntidadeNome(),
                aEntidadeId != null ? aEntidadeId : aExisting.getEntidadeId(),
                aNome != null ? aNome : aExisting.getNome(),
                aTipo != null ? aTipo : aExisting.getTipo(),
                aTamanho != null ? aTamanho : aExisting.getTamanho(),
                aUrl != null ? aUrl : aExisting.getUrl(),
                aExisting.getDataUpload());
    }

    public static Anexo from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final String aEntidadeNome,
                             final Long aEntidadeId,
                             final String aNome,
                             final String aTipo,
                             final Long aTamanho,
                             final String aUrl,
                             final Instant aDataUpload) {

        return new Anexo(
                aId != null ? AnexoId.from(aId) : null,
                aUuid != null ? AnexoUuid.from(aUuid) : null,
                aStatusDesc != null ? AnexoStatus.findByDesc(aStatusDesc) : null,
                aEntidadeNome,
                aEntidadeId,
                aNome,
                aTipo,
                aTamanho,
                aUrl,
                aDataUpload);
    }

    public static Anexo from(final Long aId) {

        return new Anexo(
                aId != null ? AnexoId.from(aId) : null,
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

    public static Anexo from(final String aUuid) {

        return new Anexo(
                null,
                aUuid != null ? AnexoUuid.from(aUuid) : null,
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

        new AnexoValidator(aHandler, this).validate();
    }

    public AnexoUuid getUuid() {
        return uuid;
    }
    public AnexoStatus getStatusCode() {
        return statusCode;
    }
    public String getEntidadeNome() {
        return entidadeNome;
    }
    public Long getEntidadeId() {
        return entidadeId;
    }
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public Long getTamanho() {
        return tamanho;
    }
    public String getUrl() {
        return url;
    }
    public Instant getDataUpload() {
        return dataUpload;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Anexo anexo = (Anexo) o;

        return Objects.equals(uuid, anexo.uuid) &&
                statusCode == anexo.statusCode &&
                Objects.equals(entidadeNome, anexo.entidadeNome) &&
                Objects.equals(entidadeId, anexo.entidadeId) &&
                Objects.equals(nome, anexo.nome) &&
                Objects.equals(tipo, anexo.tipo) &&
                Objects.equals(tamanho, anexo.tamanho) &&
                Objects.equals(url, anexo.url) &&
                Objects.equals(dataUpload, anexo.dataUpload);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                entidadeNome,
                entidadeId,
                nome,
                tipo,
                tamanho,
                url,
                dataUpload);
    }
}