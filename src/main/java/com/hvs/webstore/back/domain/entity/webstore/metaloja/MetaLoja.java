package com.hvs.webstore.back.domain.entity.webstore.metaloja;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class MetaLoja extends Entity<MetaLojaId> {

    private final MetaLojaUuid uuid;
    private final MetaLojaStatus statusCode;
    private final String chave;
    private final String valor;
    private final String descricao;
    private final TipoMetaLoja tipoMetaLoja;

    private MetaLoja(final MetaLojaId id,
                     final MetaLojaUuid uuid,
                     final MetaLojaStatus statusCode,
                     final String chave,
                     final String valor,
                     final String descricao,
                     final TipoMetaLoja tipoMetaLoja) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.chave = chave;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoMetaLoja = tipoMetaLoja;
    }

    public static MetaLoja create(final String aChave,
                                  final String aValor,
                                  final String aDescricao,
                                  final String aTipoDesc) {

        return new MetaLoja(
                MetaLojaId.from(-1L),
                MetaLojaUuid.unique(),
                MetaLojaStatus.ACTIVE,
                aChave,
                aValor,
                aDescricao,
                aTipoDesc != null ? TipoMetaLoja.findByDesc(aTipoDesc) : null);
    }

    public static MetaLoja update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final String aChave,
                                  final String aValor,
                                  final String aDescricao,
                                  final String aTipoCode) {

        return new MetaLoja(
                aId != null ? MetaLojaId.from(aId) : null,
                aUuid != null ? MetaLojaUuid.from(aUuid) : null,
                aStatusCode != null ? MetaLojaStatus.findByCode(aStatusCode) : null,
                aChave,
                aValor,
                aDescricao,
                aTipoCode != null ? TipoMetaLoja.findByCode(aTipoCode) : null);
    }

    public static MetaLoja patch(final String aStatusCode,
                                 final String aChave,
                                 final String aValor,
                                 final String aDescricao,
                                 final String aTipoCode,
                                 final MetaLoja aExisting) {

        return new MetaLoja(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? MetaLojaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aChave != null ? aChave : aExisting.getChave(),
                aValor != null ? aValor : aExisting.getValor(),
                aDescricao != null ? aDescricao : aExisting.getDescricao(),
                aTipoCode != null ? TipoMetaLoja.findByCode(aTipoCode) : aExisting.getTipoMetaLoja());
    }

    public static MetaLoja from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final String aChave,
                                final String aValor,
                                final String aDescricao,
                                final String aTipoDesc) {

        return new MetaLoja(
                aId != null ? MetaLojaId.from(aId) : null,
                aUuid != null ? MetaLojaUuid.from(aUuid) : null,
                aStatusDesc != null ? MetaLojaStatus.findByDesc(aStatusDesc) : null,
                aChave,
                aValor,
                aDescricao,
                aTipoDesc != null ? TipoMetaLoja.findByDesc(aTipoDesc) : null);
    }

    public static MetaLoja from(final Long aId) {

        return new MetaLoja(
                aId != null ? MetaLojaId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static MetaLoja from(final String aUuid) {

        return new MetaLoja(
                null,
                aUuid != null ? MetaLojaUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new MetaLojaValidator(aHandler, this).validate();
    }

    public MetaLojaUuid getUuid() {
        return uuid;
    }
    public MetaLojaStatus getStatusCode() {
        return statusCode;
    }
    public String getChave() {
        return chave;
    }
    public String getValor() {
        return valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public TipoMetaLoja getTipoMetaLoja() {
        return tipoMetaLoja;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        MetaLoja that = (MetaLoja) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(chave, that.chave) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(descricao, that.descricao) &&
                tipoMetaLoja == that.tipoMetaLoja;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                chave,
                valor,
                descricao,
                tipoMetaLoja);
    }
}
