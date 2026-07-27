package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.ReadImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.ReadImpostoOutput;
import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoId;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadImpostoUseCaseImpl extends ReadImpostoUseCase {

    private final ImpostoDomainGateway gateway;

    public ReadImpostoUseCaseImpl(ImpostoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadImpostoOutput> execute(ReadImpostoCommand aImpostoCommand) {

        Optional<Imposto> aImpostoDB = aImpostoCommand.aId() != null ?
                gateway.read(ImpostoId.from(aImpostoCommand.aId())) : gateway.readByUuid(ImpostoUuid.from(aImpostoCommand.aUuid()));

        if (aImpostoDB.isPresent())
            return Try(aImpostoDB::get).toEither().bimap(Notification::create, ReadImpostoOutput::from);

        var aImpostoId = aImpostoCommand.aId() != null ? String.valueOf(aImpostoCommand.aId()) : aImpostoCommand.aUuid();

        return Either.left(Notification.create(new Error("Imposto not found: " + aImpostoId)));
    }
}
