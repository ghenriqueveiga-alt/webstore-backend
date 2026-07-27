package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class ItemListaDesejos extends Entity<ItemListaDesejosId> {

    private final ItemListaDesejosUuid uuid;
    private final ItemListaDesejosStatus statusCode;
    private final ListaDesejos listaDesejos;
    private final Produto produto;
    private final Instant adicionadoEm;

    private ItemListaDesejos(final ItemListaDesejosId id,
                             final ItemListaDesejosUuid uuid,
                             final ItemListaDesejosStatus statusCode,
                             final ListaDesejos listaDesejos,
                             final Produto produto,
                             final Instant adicionadoEm) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.listaDesejos = listaDesejos;
        this.produto = produto;
        this.adicionadoEm = adicionadoEm;
    }

    public static ItemListaDesejos createItemListaDesejos(final Long aListaDesejosId,
                                                          final Long aProdutoId) {

        return new ItemListaDesejos(
                ItemListaDesejosId.from(-1L),
                ItemListaDesejosUuid.unique(),
                ItemListaDesejosStatus.ACTIVE,
                aListaDesejosId != null ? ListaDesejos.from(aListaDesejosId) : null,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                Instant.now());
    }

    public static ItemListaDesejos updateItemListaDesejos(final Long aId,
                                                          final String aUuid,
                                                          final String aStatusCode,
                                                          final Long aListaDesejosId,
                                                          final Long aProdutoId,
                                                          final Instant aAdicionadoEm) {

        return new ItemListaDesejos(
                aId != null ? ItemListaDesejosId.from(aId) : null,
                aUuid != null ? ItemListaDesejosUuid.from(aUuid) : null,
                aStatusCode != null ? ItemListaDesejosStatus.findByCode(aStatusCode) : null,
                aListaDesejosId != null ? ListaDesejos.from(aListaDesejosId) : null,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                aAdicionadoEm);
    }

    public static ItemListaDesejos patchItemListaDesejos(
            final String aStatusCode,
            final Long aListaDesejosId,
            final Long aProdutoId,
            final Instant aAdicionadoEm,
            final ItemListaDesejos aItemDb) {

        return new ItemListaDesejos(
                aItemDb.getId(),
                aItemDb.getUuid(),
                aStatusCode != null ? ItemListaDesejosStatus.findByCode(aStatusCode) : aItemDb.getStatusCode(),
                aListaDesejosId != null ? ListaDesejos.from(aListaDesejosId) : aItemDb.getListaDesejos(),
                aProdutoId != null ? Produto.from(aProdutoId) : aItemDb.getProduto(),
                aAdicionadoEm != null ? aAdicionadoEm : aItemDb.getAdicionadoEm());
    }

    public static ItemListaDesejos from(final Long aId,
                                        final String aUuid,
                                        final String aStatusCode,
                                        final ListaDesejos aListaDesejos,
                                        final Produto aProduto,
                                        final Instant aAdicionadoEm) {

        return new ItemListaDesejos(
                aId != null ? ItemListaDesejosId.from(aId) : null,
                aUuid != null ? ItemListaDesejosUuid.from(aUuid) : null,
                aStatusCode != null ? ItemListaDesejosStatus.findByCode(aStatusCode) : null,
                aListaDesejos,
                aProduto,
                aAdicionadoEm);
    }

    public static ItemListaDesejos from(final Long aId) {

        return new ItemListaDesejos(
                aId != null ? ItemListaDesejosId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static ItemListaDesejos from(final String aUuid) {

        return new ItemListaDesejos(
                null,
                aUuid != null ? ItemListaDesejosUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new ItemListaDesejosValidator(aHandler, this).validate();
    }

    public ItemListaDesejosUuid getUuid() {
        return uuid;
    }
    public ItemListaDesejosStatus getStatusCode() {
        return statusCode;
    }
    public ListaDesejos getListaDesejos() {
        return listaDesejos;
    }
    public Produto getProduto() {
        return produto;
    }
    public Instant getAdicionadoEm() {
        return adicionadoEm;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        ItemListaDesejos that = (ItemListaDesejos) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(listaDesejos, that.listaDesejos) &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(adicionadoEm, that.adicionadoEm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                listaDesejos,
                produto,
                adicionadoEm);
    }
}
