package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaDesejos extends Entity<ListaDesejosId> {

    private final ListaDesejosUuid uuid;
    private final ListaDesejosStatus statusCode;
    private final Usuario usuario;
    private final List<ItemListaDesejos> items;
    private final Instant criadoEm;
    private final Instant atualizadoEm;

    private ListaDesejos(final ListaDesejosId id,
                         final ListaDesejosUuid uuid,
                         final ListaDesejosStatus statusCode,
                         final Usuario usuario,
                         final List<ItemListaDesejos> items,
                         final Instant criadoEm,
                         final Instant atualizadoEm) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.usuario = usuario;
        this.items = items;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public static ListaDesejos create(final Long aUsuarioId) {

        return new ListaDesejos(
                ListaDesejosId.from(-1L),
                ListaDesejosUuid.unique(),
                ListaDesejosStatus.ACTIVE,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                null,
                Instant.now(),
                Instant.now());
    }

    public static ListaDesejos createListaDesejos(final Long aUsuarioId) {

        return new ListaDesejos(
                ListaDesejosId.from(-1L),
                ListaDesejosUuid.unique(),
                ListaDesejosStatus.ACTIVE,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                null,
                Instant.now(),
                Instant.now());
    }

    public static ListaDesejos updateListaDesejos(final Long aId,
                                                  final String aUuid,
                                                  final String aStatusCode,
                                                  final Long aUsuarioId,
                                                  final List<ItemListaDesejos> aItems,
                                                  final Instant aCriadoEm,
                                                  final Instant aAtualizadoEm) {

        return new ListaDesejos(
                aId != null ? ListaDesejosId.from(aId) : null,
                aUuid != null ? ListaDesejosUuid.from(aUuid) : null,
                aStatusCode != null ? ListaDesejosStatus.findByCode(aStatusCode) : null,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aItems,
                aCriadoEm,
                aAtualizadoEm);
    }

    public static ListaDesejos patchListaDesejos(final String aStatusCode,
                                                 final Long aUsuarioId,
                                                 final List<ItemListaDesejos> aItems,
                                                 final Instant aAtualizadoEm,
                                                 final ListaDesejos aListaDb) {

        return new ListaDesejos(
                aListaDb.getId(),
                aListaDb.getUuid(),
                aStatusCode != null ? ListaDesejosStatus.findByCode(aStatusCode) : aListaDb.getStatusCode(),
                aUsuarioId != null ? Usuario.from(aUsuarioId) : aListaDb.getUsuario(),
                aItems != null ? aItems : aListaDb.getItems(),
                aListaDb.getCriadoEm(),
                aAtualizadoEm != null ? aAtualizadoEm : aListaDb.getAtualizadoEm());
    }

    public static ListaDesejos from(final Long aId,
                                    final String aUuid,
                                    final String aStatusCode,
                                    final Usuario aUsuario,
                                    final List<ItemListaDesejos> aItems,
                                    final Instant aCriadoEm,
                                    final Instant aAtualizadoEm) {

        return new ListaDesejos(
                aId != null ? ListaDesejosId.from(aId) : null,
                aUuid != null ? ListaDesejosUuid.from(aUuid) : null,
                aStatusCode != null ? ListaDesejosStatus.findByCode(aStatusCode) : null,
                aUsuario,
                aItems,
                aCriadoEm,
                aAtualizadoEm);
    }

    public static ListaDesejos from(final Long aId) {

        return new ListaDesejos(
                aId != null ? ListaDesejosId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static ListaDesejos from(final String aUuid) {

        return new ListaDesejos(
                null,
                aUuid != null ? ListaDesejosUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public ListaDesejos adicionarItem(final Long aProdutoId) {

        final var item = ItemListaDesejos.createItemListaDesejos(id.getValue(), aProdutoId);
        final var novosItems = items != null ? new ArrayList<>(items) : new ArrayList<ItemListaDesejos>();
        novosItems.add(item);

        return new ListaDesejos(id, uuid, statusCode, usuario, novosItems, criadoEm, Instant.now());
    }

    public ListaDesejos removerItem(final String aItemUuid) {

        final var novosItems = items != null ? new ArrayList<>(items) : new ArrayList<ItemListaDesejos>();
        novosItems.removeIf(i -> i.getUuid().getValue().equals(aItemUuid));

        return new ListaDesejos(id, uuid, statusCode, usuario, novosItems, criadoEm, Instant.now());
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new ListaDesejosValidator(aHandler, this).validate();
    }

    public ListaDesejosUuid getUuid() {
        return uuid;
    }
    public ListaDesejosStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public List<ItemListaDesejos> getItems() {
        return items;
    }
    public Instant getCriadoEm() {
        return criadoEm;
    }
    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        ListaDesejos that = (ListaDesejos) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(items, that.items) &&
                Objects.equals(criadoEm, that.criadoEm) &&
                Objects.equals(atualizadoEm, that.atualizadoEm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                usuario,
                items,
                criadoEm,
                atualizadoEm);
    }
}
