package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.ReadByCupomIdCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.ReadAllCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadByCupomIdCupomPedidoUseCaseImpl extends ReadByCupomIdCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public ReadByCupomIdCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCupomPedidoOutput> execute(ReadByCupomIdCupomPedidoCommand aCupomPedidoCommand) {

        return Try(() -> gateway.readByCupomId(aCupomPedidoCommand.aCupomId()))
                .toEither().bimap(Notification::create, ReadAllCupomPedidoOutput::from);
    }
}
