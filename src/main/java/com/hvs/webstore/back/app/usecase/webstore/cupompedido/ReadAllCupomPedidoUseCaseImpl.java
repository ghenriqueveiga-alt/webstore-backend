package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.ReadAllCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.ReadAllCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllCupomPedidoUseCaseImpl extends ReadAllCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public ReadAllCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCupomPedidoOutput> execute(ReadAllCupomPedidoCommand aCupomPedidoCommand) {

        return Try(() -> gateway.readAll(aCupomPedidoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllCupomPedidoOutput::from);
    }
}
