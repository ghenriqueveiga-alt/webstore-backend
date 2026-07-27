package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.UpdateBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.UpdateBoletoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoId;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateBoletoUseCaseImpl extends UpdateBoletoUseCase {

    private final BoletoDomainGateway gateway;

    public UpdateBoletoUseCaseImpl(BoletoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateBoletoOutput> execute(UpdateBoletoCommand aBoletoCommand) {

        Optional<Boleto> aBoletoDB = aBoletoCommand.aId() != null ?
                gateway.read(BoletoId.from(aBoletoCommand.aId())) : gateway.readByUuid(BoletoUuid.from(aBoletoCommand.aUuid()));

        if (aBoletoDB.isEmpty())
            return Either.left(Notification.create(new Error("Boleto not found: " + (aBoletoCommand.aId() != null ?
                    aBoletoCommand.aId() : aBoletoCommand.aUuid()))));

        var notification = Notification.create();
        var boleto = Boleto.update(aBoletoDB.get().getId().getValue(),
                                   aBoletoDB.get().getUuid().getValue(),
                                   aBoletoCommand.aStatusCode(),
                                   aBoletoCommand.aCodigoBarras(),
                                   aBoletoCommand.aVencimento() != null ? aBoletoCommand.aVencimento().toString() : null);
        boleto.validate(notification);

        return notification.hasError() ? Left(notification) : update(boleto);
    }

    @Transactional
    private Either<Notification, UpdateBoletoOutput> update(Boleto aBoleto) {

        return Try(() -> gateway.update(aBoleto))
                .toEither().bimap(Notification::create, UpdateBoletoOutput::from);
    }
}
