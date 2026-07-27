package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.app.command.webstore.frete.ReadFreteCommand;
import com.hvs.webstore.back.app.output.webstore.frete.ReadFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteId;
import com.hvs.webstore.back.domain.entity.webstore.frete.FreteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadFreteUseCaseImpl extends ReadFreteUseCase {

    private final FreteDomainGateway gateway;

    public ReadFreteUseCaseImpl(FreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadFreteOutput> execute(ReadFreteCommand aFreteCommand) {

        Optional<Frete> aFreteDB = aFreteCommand.aId() != null ?
                gateway.read(FreteId.from(aFreteCommand.aId())) : gateway.readByUuid(FreteUuid.from(aFreteCommand.aUuid()));

        if (aFreteDB.isPresent())
            return Try(aFreteDB::get).toEither().bimap(Notification::create, ReadFreteOutput::from);

        var aFreteId = aFreteCommand.aId() != null ? String.valueOf(aFreteCommand.aId()) : aFreteCommand.aUuid();

        return Either.left(Notification.create(new Error("Frete not found: " + aFreteId)));
    }
}
