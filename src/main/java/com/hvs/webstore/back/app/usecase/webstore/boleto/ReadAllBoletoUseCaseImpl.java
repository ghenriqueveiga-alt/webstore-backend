package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.ReadAllBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.ReadAllBoletoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllBoletoUseCaseImpl extends ReadAllBoletoUseCase {

    private final BoletoDomainGateway gateway;

    public ReadAllBoletoUseCaseImpl(BoletoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllBoletoOutput> execute(ReadAllBoletoCommand aBoletoCommand) {

        return Try(() -> gateway.readAll(aBoletoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllBoletoOutput::from);
    }
}
