package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.PatchBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.PatchBoletoOutput;
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

public class PatchBoletoUseCaseImpl extends PatchBoletoUseCase {

    private final BoletoDomainGateway gateway;

    public PatchBoletoUseCaseImpl(BoletoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchBoletoOutput> execute(PatchBoletoCommand aBoletoCommand) {

        Optional<Boleto> aBoletoDB = aBoletoCommand.aId() != null ?
                gateway.read(BoletoId.from(aBoletoCommand.aId())) : gateway.readByUuid(BoletoUuid.from(aBoletoCommand.aUuid()));

        if (aBoletoDB.isEmpty())
            return Either.left(Notification.create(new Error("Boleto not found: " + (aBoletoCommand.aId() != null ?
                    aBoletoCommand.aId() : aBoletoCommand.aUuid()))));

        var notification = Notification.create();
        var boleto = Boleto.patch(aBoletoCommand.aStatusCode(),
                                  aBoletoCommand.aCodigoBarras(),
                                  aBoletoCommand.aVencimento() != null ? aBoletoCommand.aVencimento().toString() : null,
                                  aBoletoDB.get());
        boleto.validate(notification);

        return notification.hasError() ? Left(notification) : patch(boleto);
    }

    @Transactional
    private Either<Notification, PatchBoletoOutput> patch(Boleto aBoleto) {

        return Try(() -> gateway.patch(aBoleto))
                .toEither().bimap(Notification::create, PatchBoletoOutput::from);
    }
}
