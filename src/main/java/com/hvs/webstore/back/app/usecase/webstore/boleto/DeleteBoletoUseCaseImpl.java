package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.app.command.webstore.boleto.DeleteBoletoCommand;
import com.hvs.webstore.back.app.output.webstore.boleto.DeleteBoletoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoId;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteBoletoUseCaseImpl extends DeleteBoletoUseCase {

    private final BoletoDomainGateway gateway;

    public DeleteBoletoUseCaseImpl(BoletoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteBoletoOutput> execute(DeleteBoletoCommand aBoletoCommand) {

        Optional<Boleto> aBoletoDB = aBoletoCommand.aId() != null ?
                gateway.read(BoletoId.from(aBoletoCommand.aId())) : gateway.readByUuid(BoletoUuid.from(aBoletoCommand.aUuid()));

        if (aBoletoDB.isEmpty())
            return Either.left(Notification.create(new Error("Boleto not found: " + (aBoletoCommand.aId() != null ?
                    aBoletoCommand.aId() : aBoletoCommand.aUuid()))));

        return delete(aBoletoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteBoletoOutput> delete(Boleto aBoleto) {

        return Try(() -> {
            gateway.delete(aBoleto);
            return DeleteBoletoOutput.from(aBoleto);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
