package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.app.command.webstore.pedido.ReadAllPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.pedido.ReadAllPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllPedidoUseCaseImpl extends ReadAllPedidoUseCase {

    private final PedidoDomainGateway gateway;

    public ReadAllPedidoUseCaseImpl(PedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPedidoOutput> execute(ReadAllPedidoCommand aPedidoCommand) {

        Pagination<Pedido> pedidoPagination = gateway.readAll(aPedidoCommand.aSearchQuery());
        List<Pedido> lista = pedidoPagination.aContent()
                .stream().filter(pedido -> pedido.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aPedidoCommand.aSearchQuery()))
                    .toEither().bimap(Notification::create, ReadAllPedidoOutput::from);

        return Either.left(Notification.create(new Error("No Pedido was found.")));
    }
}
