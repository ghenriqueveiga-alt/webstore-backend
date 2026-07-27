package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.DeletePedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.DeletePedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoId;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;

import java.util.Optional;
import static io.vavr.API.Try;

public class DeletePedidoUseCaseImpl extends DeletePedidoUseCase {

    private final PedidoDomainGateway gateway;

    public DeletePedidoUseCaseImpl(PedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeletePedidoOutput> execute(DeletePedidoCommand aPedidoCommand) {

        Optional<Pedido> aPedidoDB = aPedidoCommand.aId() != null ?
                gateway.read(PedidoId.from(aPedidoCommand.aId())) : gateway.readByUuid(PedidoUuid.from(aPedidoCommand.aUuid()));

        if (aPedidoDB.isEmpty())
            return Either.left(Notification.create(new Error("Pedido not found: " + (aPedidoCommand.aId() != null ?
                    aPedidoCommand.aId() : aPedidoCommand.aUuid()))));

        return delete(aPedidoDB.get());
    }

    @Transactional
    private Either<Notification, DeletePedidoOutput> delete(Pedido aPedido) {

        return Try(() -> {
            gateway.delete(aPedido);
            return DeletePedidoOutput.from(aPedido);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
