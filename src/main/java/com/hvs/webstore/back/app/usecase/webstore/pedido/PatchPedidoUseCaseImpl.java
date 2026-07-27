package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.PatchPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.PatchPedidoOutput;
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

public class PatchPedidoUseCaseImpl extends PatchPedidoUseCase {

    private final PedidoDomainGateway gateway;

    public PatchPedidoUseCaseImpl(PedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchPedidoOutput> execute(PatchPedidoCommand aPedidoCommand) {

        Optional<Pedido> aPedidoDB = aPedidoCommand.aId() != null ?
                gateway.read(PedidoId.from(aPedidoCommand.aId())) : gateway.readByUuid(PedidoUuid.from(aPedidoCommand.aUuid()));

        if (aPedidoDB.isEmpty())
            return Left(Notification.create(new Error("Pedido not found: " + (aPedidoCommand.aId() != null ?
                    aPedidoCommand.aId() : aPedidoCommand.aUuid()))));

        var notification = Notification.create();
        var itemIds = aPedidoCommand.aItems() != null ?
                aPedidoCommand.aItems().stream().map(item -> item.aProdutoId()).toList() : null;
        var pedido = Pedido.patch(aPedidoCommand.aStatusCode(),
                                  aPedidoCommand.aEnderecoEntregaId(),
                                  aPedidoCommand.aFormaPagamentoId(),
                                  aPedidoCommand.aDataPagamento(),
                                  aPedidoCommand.aDataEnvio(),
                                  aPedidoCommand.aTotal(),
                                  itemIds,
                                  aPedidoDB.get());
        pedido.validate(notification);

        return notification.hasError() ? Left(notification) : patch(pedido);
    }
    @Transactional
    private Either<Notification, PatchPedidoOutput> patch(Pedido aPedido) {

        return Try(() -> gateway.patch(aPedido))
                .toEither().bimap(Notification::create, PatchPedidoOutput::from);
    }
}
