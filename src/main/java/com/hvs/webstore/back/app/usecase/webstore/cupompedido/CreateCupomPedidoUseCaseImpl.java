package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.CreateCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.CreateCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCupomPedidoUseCaseImpl extends CreateCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public CreateCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCupomPedidoOutput> execute(CreateCupomPedidoCommand aCupomPedidoCommand) {

        var notification = Notification.create();
        var cupomPedido = CupomPedido.create(aCupomPedidoCommand.aCupomId(),
                                             aCupomPedidoCommand.aPedidoId(),
                                             aCupomPedidoCommand.aValorDesconto());
        cupomPedido.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(cupomPedido);
    }

    @Transactional
    private Either<Notification, CreateCupomPedidoOutput> create(CupomPedido aCupomPedido) {

        return Try(() -> gateway.create(aCupomPedido))
                .toEither().bimap(Notification::create, CreateCupomPedidoOutput::from);
    }
}
