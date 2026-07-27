package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.ReadBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.ReadBoletoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoId;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadBoletoUseCaseImpl extends ReadBoletoUseCase {

    private final BoletoDomainGateway gateway;

    public ReadBoletoUseCaseImpl(BoletoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadBoletoOutput> execute(ReadBoletoCommand aBoletoCommand) {

        Optional<Boleto> aBoletoDB = aBoletoCommand.aId() != null ?
                gateway.read(BoletoId.from(aBoletoCommand.aId())) : gateway.readByUuid(BoletoUuid.from(aBoletoCommand.aUuid()));

        if (aBoletoDB.isPresent())
            return Try(aBoletoDB::get).toEither().bimap(Notification::create, ReadBoletoOutput::from);

        var aBoletoId = aBoletoCommand.aId() != null ? String.valueOf(aBoletoCommand.aId()) : aBoletoCommand.aUuid();

        return Either.left(Notification.create(new Error("Boleto not found: " + aBoletoId)));
    }
}
