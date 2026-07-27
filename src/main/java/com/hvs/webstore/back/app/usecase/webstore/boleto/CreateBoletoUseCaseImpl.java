package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.CreateBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.CreateBoletoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateBoletoUseCaseImpl extends CreateBoletoUseCase {

    private final BoletoDomainGateway gateway;

    public CreateBoletoUseCaseImpl(BoletoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateBoletoOutput> execute(CreateBoletoCommand aBoletoCommand) {

        var notification = Notification.create();
        var boleto = Boleto.create(aBoletoCommand.aCodigoBarras(),
                                   aBoletoCommand.aVencimento() != null ? aBoletoCommand.aVencimento().toString() : null);
        boleto.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(boleto);
    }

    @Transactional
    private Either<Notification, CreateBoletoOutput> create(Boleto aBoleto) {

        return Try(() -> gateway.create(aBoleto))
                .toEither().bimap(Notification::create, CreateBoletoOutput::from);
    }
}
