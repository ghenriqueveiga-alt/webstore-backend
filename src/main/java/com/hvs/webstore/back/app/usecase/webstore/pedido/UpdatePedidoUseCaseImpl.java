package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.UpdatePedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.UpdatePedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoId;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdatePedidoUseCaseImpl extends UpdatePedidoUseCase {

    private final PedidoDomainGateway gateway;

    public UpdatePedidoUseCaseImpl(PedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdatePedidoOutput> execute(UpdatePedidoCommand aPedidoCommand) {

        Optional<Pedido> aPedidoDB = aPedidoCommand.aId() != null ?
                gateway.read(PedidoId.from(aPedidoCommand.aId())) : gateway.readByUuid(PedidoUuid.from(aPedidoCommand.aUuid()));

        if (aPedidoDB.isEmpty())
            return Left(Notification.create(new Error("Pedido not found: " + (aPedidoCommand.aId() != null ?
                    aPedidoCommand.aId() : aPedidoCommand.aUuid()))));

        var notification = Notification.create();
        var itemIds = aPedidoCommand.aItems() != null ?
                aPedidoCommand.aItems().stream().map(item -> item.aProdutoId()).toList() : null;

        var pedido = Pedido.update(aPedidoDB.get().getId().getValue(),
                                   aPedidoDB.get().getUuid().getValue(),
                                   aPedidoCommand.aStatusCode(),
                                   aPedidoCommand.aUsuarioId(),
                                   aPedidoCommand.aEnderecoEntregaId(),
                                   aPedidoCommand.aFormaPagamentoId(),
                                   aPedidoCommand.aDataCriacao(),
                                   aPedidoCommand.aDataPagamento(),
                                   aPedidoCommand.aDataEnvio(),
                                   aPedidoCommand.aTotal(),
                                   itemIds);
        pedido.validate(notification);

        return notification.hasError() ? Left(notification) : update(pedido);
    }
    @Transactional
    private Either<Notification, UpdatePedidoOutput> update(Pedido aPedido) {

        return Try(() -> gateway.update(aPedido))
                .toEither().bimap(Notification::create, UpdatePedidoOutput::from);
    }
}
