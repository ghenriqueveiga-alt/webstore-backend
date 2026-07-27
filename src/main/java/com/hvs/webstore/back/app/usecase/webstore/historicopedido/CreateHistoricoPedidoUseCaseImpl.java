package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.CreateHistoricoPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.CreateHistoricoPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateHistoricoPedidoUseCaseImpl extends CreateHistoricoPedidoUseCase {

    private final HistoricoPedidoDomainGateway gateway;

    public CreateHistoricoPedidoUseCaseImpl(HistoricoPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateHistoricoPedidoOutput> execute(CreateHistoricoPedidoCommand aHistoricoPedidoCommand) {

        var notification = Notification.create();
        var historico = HistoricoPedido.create(aHistoricoPedidoCommand.aPedidoId(),
                                               aHistoricoPedidoCommand.aStatusAnterior(),
                                               aHistoricoPedidoCommand.aStatusNovo(),
                                               aHistoricoPedidoCommand.aObservacao(),
                                               aHistoricoPedidoCommand.aCriadoPor());
        historico.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(historico);
    }

    @Transactional
    private Either<Notification, CreateHistoricoPedidoOutput> create(HistoricoPedido aHistoricoPedido) {

        return Try(() -> gateway.create(aHistoricoPedido))
                .toEither().bimap(Notification::create, CreateHistoricoPedidoOutput::from);
    }
}
