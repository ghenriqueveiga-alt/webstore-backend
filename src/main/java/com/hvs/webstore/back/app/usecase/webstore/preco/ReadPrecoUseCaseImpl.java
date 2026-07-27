package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.ReadPrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.ReadPrecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoId;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadPrecoUseCaseImpl extends ReadPrecoUseCase {

    private final PrecoDomainGateway gateway;

    public ReadPrecoUseCaseImpl(PrecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadPrecoOutput> execute(ReadPrecoCommand aPrecoCommand) {

        Optional<Preco> aPrecoDB = aPrecoCommand.aId() != null ?
                gateway.read(PrecoId.from(aPrecoCommand.aId())) : gateway.readByUuid(PrecoUuid.from(aPrecoCommand.aUuid()));

        if (aPrecoDB.isPresent())
            return Try(aPrecoDB::get).toEither().bimap(Notification::create, ReadPrecoOutput::from);

        var aPrecoId = aPrecoCommand.aId() != null ? String.valueOf(aPrecoCommand.aId()) : aPrecoCommand.aUuid();

        return Either.left(Notification.create(new Error("Preco not found: " + aPrecoId)));
    }
}
