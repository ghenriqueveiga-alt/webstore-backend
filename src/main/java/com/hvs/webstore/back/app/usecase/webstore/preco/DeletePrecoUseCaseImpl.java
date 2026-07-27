package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.DeletePrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.DeletePrecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoId;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeletePrecoUseCaseImpl extends DeletePrecoUseCase {

    private final PrecoDomainGateway gateway;

    public DeletePrecoUseCaseImpl(PrecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeletePrecoOutput> execute(DeletePrecoCommand aPrecoCommand) {

        Optional<Preco> aPrecoDB = aPrecoCommand.aId() != null ?
                gateway.read(PrecoId.from(aPrecoCommand.aId())) : gateway.readByUuid(PrecoUuid.from(aPrecoCommand.aUuid()));

        if (aPrecoDB.isEmpty())
            return Either.left(Notification.create(new Error("Preco not found: " + (aPrecoCommand.aId() != null ?
                    aPrecoCommand.aId() : aPrecoCommand.aUuid()))));

        return delete(aPrecoDB.get());
    }

    @Transactional
    private Either<Notification, DeletePrecoOutput> delete(Preco aPreco) {

        return Try(() -> {
            gateway.delete(aPreco);
            return DeletePrecoOutput.from(aPreco);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
