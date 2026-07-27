package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrinho extends Entity<CarrinhoId> {

    private final CarrinhoUuid uuid;
    private final CarrinhoStatus statusCode;
    private final Usuario usuario;
    private final List<ItemCarrinho> items;
    private final Instant criadoEm;
    private final Instant atualizadoEm;

    private Carrinho(final CarrinhoId id,
                     final CarrinhoUuid uuid,
                     final CarrinhoStatus statusCode,
                     final Usuario usuario,
                     final List<ItemCarrinho> items,
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

    public static Carrinho create(final Long aUsuarioId) {

        return new Carrinho(
                CarrinhoId.from(-1L),
                CarrinhoUuid.unique(),
                CarrinhoStatus.ACTIVE,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                new ArrayList<>(),
                Instant.now(),
                null);
    }

    public Carrinho adicionarItem(final Long aProdutoId, final Integer aQuantidade) {

        final var item = ItemCarrinho.create(aProdutoId, aQuantidade);
        final var novosItems = items != null ? new ArrayList<>(items) : new ArrayList<ItemCarrinho>();
        novosItems.add(item);

        return new Carrinho(id, uuid, statusCode, usuario, novosItems, criadoEm, Instant.now());
    }

    public Carrinho removerItem(final String aItemUuid) {

        final var novosItems = items != null ? new ArrayList<>(items) : new ArrayList<ItemCarrinho>();
        novosItems.removeIf(i -> i.getUuid().getValue().equals(aItemUuid));

        return new Carrinho(id, uuid, statusCode, usuario, novosItems, criadoEm, Instant.now());
    }

    public Carrinho limpar() {

        return new Carrinho(id, uuid, statusCode, usuario, new ArrayList<>(), criadoEm, Instant.now());
    }

    public Carrinho atualizarQuantidade(final String aItemUuid, final Integer aQuantidade) {

        final var novosItems = items != null ? new ArrayList<>(items) : new ArrayList<ItemCarrinho>();
        for (int i = 0; i < novosItems.size(); i++) {
            final var item = novosItems.get(i);
            if (item.getUuid().getValue().equals(aItemUuid)) {
                novosItems.set(i, ItemCarrinho.from(
                        item.getId().getValue(),
                        item.getUuid().getValue(),
                        item.getStatusCode().getCode(),
                        item.getProduto(),
                        aQuantidade));
                break;
            }
        }

        return new Carrinho(id, uuid, statusCode, usuario, novosItems, criadoEm, Instant.now());
    }

    public Carrinho finalizar() {

        return new Carrinho(id, uuid, CarrinhoStatus.INACTIVE, usuario, items, criadoEm, Instant.now());
    }

    public static Carrinho from(final Long aId,
                                 final String aUuid,
                                 final String aStatusCode,
                                 final Usuario aUsuario,
                                 final List<ItemCarrinho> aItems,
                                 final Instant aCriadoEm,
                                 final Instant aAtualizadoEm) {

        return new Carrinho(
                aId != null ? CarrinhoId.from(aId) : null,
                aUuid != null ? CarrinhoUuid.from(aUuid) : null,
                aStatusCode != null ? CarrinhoStatus.findByCode(aStatusCode) : null,
                aUsuario,
                aItems,
                aCriadoEm,
                aAtualizadoEm);
    }

    public static Carrinho from(final Long aId) {

        return new Carrinho(
                aId != null ? CarrinhoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Carrinho from(final String aUuid) {

        return new Carrinho(
                null,
                aUuid != null ? CarrinhoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CarrinhoValidator(aHandler, this).validate();
    }

    public CarrinhoUuid getUuid() {
        return uuid;
    }
    public CarrinhoStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public List<ItemCarrinho> getItems() {
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

        Carrinho carrinho = (Carrinho) o;

        return Objects.equals(uuid, carrinho.uuid) &&
                statusCode == carrinho.statusCode &&
                Objects.equals(usuario, carrinho.usuario) &&
                Objects.equals(items, carrinho.items) &&
                Objects.equals(criadoEm, carrinho.criadoEm) &&
                Objects.equals(atualizadoEm, carrinho.atualizadoEm);
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
