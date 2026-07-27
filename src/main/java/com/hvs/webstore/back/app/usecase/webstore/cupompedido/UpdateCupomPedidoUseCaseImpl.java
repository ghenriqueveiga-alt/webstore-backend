package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.UpdateCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.UpdateCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoId;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateCupomPedidoUseCaseImpl extends UpdateCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public UpdateCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCupomPedidoOutput> execute(UpdateCupomPedidoCommand aCupomPedidoCommand) {

        Optional<CupomPedido> aCupomPedidoDB = aCupomPedidoCommand.aId() != null ?
                gateway.read(CupomPedidoId.from(aCupomPedidoCommand.aId())) : gateway.readByUuid(CupomPedidoUuid.from(aCupomPedidoCommand.aUuid()));

        if (aCupomPedidoDB.isEmpty())
            return Either.left(Notification.create(new Error("CupomPedido not found: " + (aCupomPedidoCommand.aId() != null ?
                    aCupomPedidoCommand.aId() : aCupomPedidoCommand.aUuid()))));

        var notification = Notification.create();
        var cupomPedido = CupomPedido.update(aCupomPedidoDB.get().getId().getValue(),
                                              aCupomPedidoDB.get().getUuid().getValue(),
                                              aCupomPedidoCommand.aStatusCode(),
                                              aCupomPedidoCommand.aCupomId(),
                                              aCupomPedidoCommand.aPedidoId(),
                                              aCupomPedidoCommand.aValorDesconto());
        cupomPedido.validate(notification);

        return notification.hasError() ? Left(notification) : update(cupomPedido);
    }

    @Transactional
    private Either<Notification, UpdateCupomPedidoOutput> update(CupomPedido aCupomPedido) {

        return Try(() -> gateway.update(aCupomPedido))
                .toEither().bimap(Notification::create, UpdateCupomPedidoOutput::from);
    }
}
