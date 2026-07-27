package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.ReadCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.ReadCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoId;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCupomPedidoUseCaseImpl extends ReadCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public ReadCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCupomPedidoOutput> execute(ReadCupomPedidoCommand aCupomPedidoCommand) {

        Optional<CupomPedido> aCupomPedidoDB = aCupomPedidoCommand.aId() != null ?
                gateway.read(CupomPedidoId.from(aCupomPedidoCommand.aId())) : gateway.readByUuid(CupomPedidoUuid.from(aCupomPedidoCommand.aUuid()));

        if (aCupomPedidoDB.isPresent())
            return Try(aCupomPedidoDB::get).toEither().bimap(Notification::create, ReadCupomPedidoOutput::from);

        var aCupomPedidoId = aCupomPedidoCommand.aId() != null ? String.valueOf(aCupomPedidoCommand.aId()) : aCupomPedidoCommand.aUuid();

        return Either.left(Notification.create(new Error("CupomPedido not found: " + aCupomPedidoId)));
    }
}
