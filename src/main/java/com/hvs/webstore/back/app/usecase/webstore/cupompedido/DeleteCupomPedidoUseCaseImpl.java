package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.app.command.webstore.cupompedido.DeleteCupomPedidoCommand;
import com.hvs.webstore.back.app.output.webstore.cupompedido.DeleteCupomPedidoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoId;
import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCupomPedidoUseCaseImpl extends DeleteCupomPedidoUseCase {

    private final CupomPedidoDomainGateway gateway;

    public DeleteCupomPedidoUseCaseImpl(CupomPedidoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCupomPedidoOutput> execute(DeleteCupomPedidoCommand aCupomPedidoCommand) {

        Optional<CupomPedido> aCupomPedidoDB = aCupomPedidoCommand.aId() != null ?
                gateway.read(CupomPedidoId.from(aCupomPedidoCommand.aId())) : gateway.readByUuid(CupomPedidoUuid.from(aCupomPedidoCommand.aUuid()));

        if (aCupomPedidoDB.isEmpty())
            return Either.left(Notification.create(new Error("CupomPedido not found: " + (aCupomPedidoCommand.aId() != null ?
                    aCupomPedidoCommand.aId() : aCupomPedidoCommand.aUuid()))));

        return delete(aCupomPedidoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteCupomPedidoOutput> delete(CupomPedido aCupomPedido) {

        return Try(() -> {
            gateway.delete(aCupomPedido);
            return DeleteCupomPedidoOutput.from(aCupomPedido);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
