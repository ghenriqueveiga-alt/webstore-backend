package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.PatchCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.PatchCupomPedidoOutput;
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

public class PatchCupomPedidoUseCaseImpl extends PatchCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public PatchCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCupomPedidoOutput> execute(PatchCupomPedidoCommand aCupomPedidoCommand) {

        Optional<CupomPedido> aCupomPedidoDB = aCupomPedidoCommand.aId() != null ?
                gateway.read(CupomPedidoId.from(aCupomPedidoCommand.aId())) : gateway.readByUuid(CupomPedidoUuid.from(aCupomPedidoCommand.aUuid()));

        if (aCupomPedidoDB.isEmpty())
            return Either.left(Notification.create(new Error("CupomPedido not found: " + (aCupomPedidoCommand.aId() != null ?
                    aCupomPedidoCommand.aId() : aCupomPedidoCommand.aUuid()))));

        var notification = Notification.create();
        var cupomPedido = CupomPedido.patch(aCupomPedidoCommand.aStatusCode(),
                                             aCupomPedidoCommand.aCupomId(),
                                             aCupomPedidoCommand.aPedidoId(),
                                             aCupomPedidoCommand.aValorDesconto(),
                                             aCupomPedidoDB.get());
        cupomPedido.validate(notification);

        return notification.hasError() ? Left(notification) : patch(cupomPedido);
    }

    @Transactional
    private Either<Notification, PatchCupomPedidoOutput> patch(CupomPedido aCupomPedido) {

        return Try(() -> gateway.patch(aCupomPedido))
                .toEither().bimap(Notification::create, PatchCupomPedidoOutput::from);
    }
}
