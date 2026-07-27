package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.ReadByPedidoIdCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.ReadAllCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadByPedidoIdCupomPedidoUseCaseImpl extends ReadByPedidoIdCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public ReadByPedidoIdCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCupomPedidoOutput> execute(ReadByPedidoIdCupomPedidoCommand aCupomPedidoCommand) {

        return Try(() -> gateway.readByPedidoId(aCupomPedidoCommand.aPedidoId()))
                .toEither().bimap(Notification::create, ReadAllCupomPedidoOutput::from);
    }
}
