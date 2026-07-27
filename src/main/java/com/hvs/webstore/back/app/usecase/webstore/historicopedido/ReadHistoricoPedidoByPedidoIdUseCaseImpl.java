package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.ReadHistoricoPedidoByPedidoIdCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.ReadAllHistoricoPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadHistoricoPedidoByPedidoIdUseCaseImpl extends ReadHistoricoPedidoByPedidoIdUseCase {

    private final HistoricoPedidoDomainGateway gateway;

    public ReadHistoricoPedidoByPedidoIdUseCaseImpl(HistoricoPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllHistoricoPedidoOutput> execute(ReadHistoricoPedidoByPedidoIdCommand aHistoricoPedidoCommand) {

        return Try(() -> gateway.readByPedidoId(aHistoricoPedidoCommand.aPedidoId()))
                .toEither().bimap(Notification::create, ReadAllHistoricoPedidoOutput::from);
    }
}
