package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.app.command.webstore.historicopedido.ReadHistoricoPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.historicopedido.ReadHistoricoPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoId;
import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadHistoricoPedidoUseCaseImpl extends ReadHistoricoPedidoUseCase {

    private final HistoricoPedidoDomainGateway gateway;

    public ReadHistoricoPedidoUseCaseImpl(HistoricoPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadHistoricoPedidoOutput> execute(ReadHistoricoPedidoCommand aHistoricoPedidoCommand) {

        Optional<HistoricoPedido> aHistoricoPedidoDB = aHistoricoPedidoCommand.aId() != null ?
                gateway.read(HistoricoPedidoId.from(aHistoricoPedidoCommand.aId())) : gateway.readByUuid(HistoricoPedidoUuid.from(aHistoricoPedidoCommand.aUuid()));

        if (aHistoricoPedidoDB.isPresent())
            return Try(aHistoricoPedidoDB::get).toEither().bimap(Notification::create, ReadHistoricoPedidoOutput::from);

        var aHistoricoPedidoId = aHistoricoPedidoCommand.aId() != null ? String.valueOf(aHistoricoPedidoCommand.aId()) : aHistoricoPedidoCommand.aUuid();

        return Either.left(Notification.create(new Error("HistoricoPedido not found: " + aHistoricoPedidoId)));
    }
}
