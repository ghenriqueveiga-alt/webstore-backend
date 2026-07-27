package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Pedido extends Entity<PedidoId> {

    private final PedidoUuid uuid;
    private final PedidoStatus statusCode;
    private final Usuario usuario;
    private final Endereco enderecoEntrega;
    private final FormaPagamento formaPagamento;
    private final Instant dataCriacao;
    private final Instant dataPagamento;
    private final Instant dataEnvio;
    private final Long total;
    private final List<ItemPedido> items;

    private Pedido(final PedidoId id,
                   final PedidoUuid uuid,
                   final PedidoStatus statusCode,
                   final Usuario usuario,
                   final Endereco enderecoEntrega,
                   final FormaPagamento formaPagamento,
                   final Instant dataCriacao,
                   final Instant dataPagamento,
                   final Instant dataEnvio,
                   final Long total,
                   final List<ItemPedido> items) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.usuario = usuario;
        this.enderecoEntrega = enderecoEntrega;
        this.formaPagamento = formaPagamento;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataEnvio = dataEnvio;
        this.total = total;
        this.items = items;
    }

    public static Pedido create(final Long aUsuarioId,
                                final Long aEnderecoEntregaId,
                                final Long aFormaPagamentoId,
                                final List<Long> aItemIds) {

        final var id = PedidoId.from(-1L);
        final var uuid = PedidoUuid.unique();
        final var status = PedidoStatus.ACTIVE;
        final var dataCriacao = Instant.now();
        final var usuario = aUsuarioId != null ? Usuario.from(aUsuarioId) : null;
        final var enderecoEntrega = aEnderecoEntregaId != null ? Endereco.from(aEnderecoEntregaId) : null;
        final var formaPagamento = aFormaPagamentoId != null ? FormaPagamento.from(aFormaPagamentoId) : null;
        final var items = aItemIds != null ? aItemIds.stream().map(ItemPedido::from).toList() : null;

        return new Pedido(
                id,
                uuid,
                status,
                usuario,
                enderecoEntrega,
                formaPagamento,
                dataCriacao,
                null,
                null,
                null,
                items);
    }

    public static Pedido update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final Long aUsuarioId,
                                final Long aEnderecoEntregaId,
                                final Long aFormaPagamentoId,
                                final Instant aDataCriacao,
                                final Instant aDataPagamento,
                                final Instant aDataEnvio,
                                final Long aTotal,
                                final List<Long> aItemIds) {

        final var id = aId != null ? PedidoId.from(aId) : null;
        final var uuid = aUuid != null ? PedidoUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? PedidoStatus.findByCode(aStatusCode) : null;
        final var usuario = aUsuarioId != null ? Usuario.from(aUsuarioId) : null;
        final var enderecoEntrega = aEnderecoEntregaId != null ? Endereco.from(aEnderecoEntregaId) : null;
        final var formaPagamento = aFormaPagamentoId != null ? FormaPagamento.from(aFormaPagamentoId) : null;
        final var items = aItemIds != null ? aItemIds.stream().map(ItemPedido::from).toList() : null;

        return new Pedido(
                id,
                uuid,
                status,
                usuario,
                enderecoEntrega,
                formaPagamento,
                aDataCriacao,
                aDataPagamento,
                aDataEnvio,
                aTotal,
                items);
    }

    public static Pedido patch(final String aStatusCode,
                               final Long aEnderecoEntregaId,
                               final Long aFormaPagamentoId,
                               final Instant aDataPagamento,
                               final Instant aDataEnvio,
                               final Long aTotal,
                               final List<Long> aItemIds,
                               final Pedido aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? PedidoStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var usuario = aExisting.getUsuario();
        final var enderecoEntrega = aEnderecoEntregaId != null ? Endereco.from(aEnderecoEntregaId) : aExisting.getEnderecoEntrega();
        final var formaPagamento = aFormaPagamentoId != null ? FormaPagamento.from(aFormaPagamentoId) : aExisting.getFormaPagamento();
        final var dataCriacao = aExisting.getDataCriacao();
        final var dataPagamento = aDataPagamento != null ? aDataPagamento : aExisting.getDataPagamento();
        final var dataEnvio = aDataEnvio != null ? aDataEnvio : aExisting.getDataEnvio();
        final var total = aTotal != null ? aTotal : aExisting.getTotal();
        final var items = aItemIds != null ? aItemIds.stream().map(ItemPedido::from).toList() : aExisting.getItems();

        return new Pedido(
                id,
                uuid,
                status,
                usuario,
                enderecoEntrega,
                formaPagamento,
                dataCriacao,
                dataPagamento,
                dataEnvio,
                total,
                items);
    }

    public static Pedido from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final Usuario aUsuario,
                              final Endereco aEnderecoEntrega,
                              final FormaPagamento aFormaPagamento,
                              final Instant aDataCriacao,
                              final Instant aDataPagamento,
                              final Instant aDataEnvio,
                              final Long aTotal,
                              final List<ItemPedido> aItems) {

        final var id = aId != null ? PedidoId.from(aId) : null;
        final var uuid = aUuid != null ? PedidoUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? PedidoStatus.findByDesc(aStatusDesc) : null;

        return new Pedido(
                id,
                uuid,
                status,
                aUsuario,
                aEnderecoEntrega,
                aFormaPagamento,
                aDataCriacao,
                aDataPagamento,
                aDataEnvio,
                aTotal,
                aItems);
    }

    public static Pedido from(final Long aId) {

        return new Pedido(
                aId != null ? PedidoId.from(aId) : null,
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

    public static Pedido from(final String aUuid) {

        return new Pedido(
                null,
                aUuid != null ? PedidoUuid.from(aUuid) : null,
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

        new PedidoValidator(aHandler, this).validate();
    }

    public PedidoUuid getUuid() {
        return uuid;
    }
    public PedidoStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public Instant getDataCriacao() {
        return dataCriacao;
    }
    public Instant getDataPagamento() {
        return dataPagamento;
    }
    public Instant getDataEnvio() {
        return dataEnvio;
    }
    public Long getTotal() {
        return total;
    }
    public List<ItemPedido> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Pedido pedido = (Pedido) o;

        return Objects.equals(uuid, pedido.uuid) &&
                statusCode == pedido.statusCode &&
                Objects.equals(usuario, pedido.usuario) &&
                Objects.equals(enderecoEntrega, pedido.enderecoEntrega) &&
                Objects.equals(formaPagamento, pedido.formaPagamento) &&
                Objects.equals(dataCriacao, pedido.dataCriacao) &&
                Objects.equals(dataPagamento, pedido.dataPagamento) &&
                Objects.equals(dataEnvio, pedido.dataEnvio) &&
                Objects.equals(total, pedido.total) &&
                Objects.equals(items, pedido.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                usuario,
                enderecoEntrega,
                formaPagamento,
                dataCriacao,
                dataPagamento,
                dataEnvio,
                total,
                items);
    }
}
