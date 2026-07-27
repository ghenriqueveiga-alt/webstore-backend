package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.ReadAllHistoricoPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.ReadAllHistoricoPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllHistoricoPedidoUseCaseImpl extends ReadAllHistoricoPedidoUseCase {

    private final HistoricoPedidoDomainGateway gateway;

    public ReadAllHistoricoPedidoUseCaseImpl(HistoricoPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllHistoricoPedidoOutput> execute(ReadAllHistoricoPedidoCommand aHistoricoPedidoCommand) {

        return Try(() -> gateway.readAll(aHistoricoPedidoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllHistoricoPedidoOutput::from);
    }
}
