package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.CreatePedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.CreatePedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreatePedidoUseCaseImpl extends CreatePedidoUseCase {

    private final PedidoDomainGateway gateway;

    public CreatePedidoUseCaseImpl(PedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreatePedidoOutput> execute(CreatePedidoCommand aPedidoCommand) {

        var notification = Notification.create();
        var itemIds = aPedidoCommand.aItems() != null ?
                aPedidoCommand.aItems().stream().map(item -> item.aProdutoId()).toList() : null;
        var pedido = Pedido.create(aPedidoCommand.aUsuarioId(),
                                   aPedidoCommand.aEnderecoEntregaId(),
                                   aPedidoCommand.aFormaPagamentoId(),
                                   itemIds);
        pedido.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(pedido);
    }

    @Transactional
    private Either<Notification, CreatePedidoOutput> create(Pedido aPedido) {

        return Try(() -> gateway.create(aPedido))
                .toEither().bimap(Notification::create, CreatePedidoOutput::from);
    }
}
