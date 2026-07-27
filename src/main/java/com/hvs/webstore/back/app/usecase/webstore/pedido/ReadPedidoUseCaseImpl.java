package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.ReadPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.ReadPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoId;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadPedidoUseCaseImpl extends ReadPedidoUseCase {

    private final PedidoDomainGateway gateway;

    public ReadPedidoUseCaseImpl(PedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadPedidoOutput> execute(ReadPedidoCommand aPedidoCommand) {

        Optional<Pedido> aPedidoDB = aPedidoCommand.aId() != null ?
                gateway.read(PedidoId.from(aPedidoCommand.aId())) : gateway.readByUuid(PedidoUuid.from(aPedidoCommand.aUuid()));

        if (aPedidoDB.isPresent())
            return Try(aPedidoDB::get).toEither().bimap(Notification::create, ReadPedidoOutput::from);

        var aPedidoId = aPedidoCommand.aId() != null ? String.valueOf(aPedidoCommand.aId()) : aPedidoCommand.aUuid();

        return Either.left(Notification.create(new Error("Pedido not found: " + aPedidoId)));
    }
}
