package com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class CategoriaHierarquia extends Entity<CategoriaHierarquiaId> {

    private final CategoriaHierarquiaUuid uuid;
    private final CategoriaHierarquiaStatus statusCode;
    private final Categoria categoria;
    private final Categoria categoriaPai;
    private final Integer nivel;

    private CategoriaHierarquia(final CategoriaHierarquiaId id,
                                final CategoriaHierarquiaUuid uuid,
                                final CategoriaHierarquiaStatus statusCode,
                                final Categoria categoria,
                                final Categoria categoriaPai,
                                final Integer nivel) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.categoria = categoria;
        this.categoriaPai = categoriaPai;
        this.nivel = nivel;
    }

    public static CategoriaHierarquia create(final Long aCategoriaId,
                                             final Long aCategoriaPaiId,
                                             final Integer aNivel) {

        return new CategoriaHierarquia(
                CategoriaHierarquiaId.from(-1L),
                CategoriaHierarquiaUuid.unique(),
                CategoriaHierarquiaStatus.ACTIVE,
                aCategoriaId != null ? Categoria.from(aCategoriaId) : null,
                aCategoriaPaiId != null ? Categoria.from(aCategoriaPaiId) : null,
                aNivel);
    }

    public static CategoriaHierarquia update(final Long aId,
                                             final String aUuid,
                                             final String aStatusCode,
                                             final Long aCategoriaId,
                                             final Long aCategoriaPaiId,
                                             final Integer aNivel) {

        return new CategoriaHierarquia(
                aId != null ? CategoriaHierarquiaId.from(aId) : null,
                aUuid != null ? CategoriaHierarquiaUuid.from(aUuid) : null,
                aStatusCode != null ? CategoriaHierarquiaStatus.findByCode(aStatusCode) : null,
                aCategoriaId != null ? Categoria.from(aCategoriaId) : null,
                aCategoriaPaiId != null ? Categoria.from(aCategoriaPaiId) : null,
                aNivel);
    }

    public static CategoriaHierarquia patch(final String aStatusCode,
                                            final Long aCategoriaId,
                                            final Long aCategoriaPaiId,
                                            final Integer aNivel,
                                            final CategoriaHierarquia aExisting) {

        return new CategoriaHierarquia(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CategoriaHierarquiaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aCategoriaId != null ? Categoria.from(aCategoriaId) : aExisting.getCategoria(),
                aCategoriaPaiId != null ? Categoria.from(aCategoriaPaiId) : aExisting.getCategoriaPai(),
                aNivel != null ? aNivel : aExisting.getNivel());
    }

    public static CategoriaHierarquia from(final Long aId,
                                           final String aUuid,
                                           final String aStatusDesc,
                                           final Categoria aCategoria,
                                           final Categoria aCategoriaPai,
                                           final Integer aNivel) {

        return new CategoriaHierarquia(
                aId != null ? CategoriaHierarquiaId.from(aId) : null,
                aUuid != null ? CategoriaHierarquiaUuid.from(aUuid) : null,
                aStatusDesc != null ? CategoriaHierarquiaStatus.findByDesc(aStatusDesc) : null,
                aCategoria,
                aCategoriaPai,
                aNivel);
    }

    public static CategoriaHierarquia from(final Long aId) {

        return new CategoriaHierarquia(
                aId != null ? CategoriaHierarquiaId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static CategoriaHierarquia from(final String aUuid) {

        return new CategoriaHierarquia(
                null,
                aUuid != null ? CategoriaHierarquiaUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CategoriaHierarquiaValidator(aHandler, this).validate();
    }

    public CategoriaHierarquiaUuid getUuid() {
        return uuid;
    }
    public CategoriaHierarquiaStatus getStatusCode() {
        return statusCode;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public Categoria getCategoriaPai() {
        return categoriaPai;
    }
    public Integer getNivel() {
        return nivel;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        CategoriaHierarquia that = (CategoriaHierarquia) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(categoria, that.categoria) &&
                Objects.equals(categoriaPai, that.categoriaPai) &&
                Objects.equals(nivel, that.nivel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                categoria,
                categoriaPai,
                nivel);
    }
}