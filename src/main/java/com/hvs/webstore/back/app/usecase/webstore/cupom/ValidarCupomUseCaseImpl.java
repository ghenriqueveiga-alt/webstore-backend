package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.ValidarCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.CupomOutput;
import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomStatus;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.time.Instant;
import java.util.Optional;
import static io.vavr.API.Try;

public class ValidarCupomUseCaseImpl extends ValidarCupomUseCase {

    private final CupomDomainGateway gateway;

    public ValidarCupomUseCaseImpl(CupomDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CupomOutput> execute(ValidarCupomCommand aCupomCommand) {

        Optional<Cupom> cupomDb = gateway.readByCodigo(aCupomCommand.codigo());

        if (cupomDb.isEmpty())
            return Either.left(Notification.create(new Error("The Cupom with codigo: " + aCupomCommand.codigo() + " could not be found.")));

        var cupom = cupomDb.get();

        if (cupom.getStatusCode() != CupomStatus.ACTIVE)
            return Either.left(Notification.create(new Error("The Cupom with codigo: " + aCupomCommand.codigo() + " is not active.")));

        if (!cupom.getAtivo())
            return Either.left(Notification.create(new Error("The Cupom with codigo: " + aCupomCommand.codigo() + " is inactive.")));

        if (cupom.getDataExpiracao() != null && cupom.getDataExpiracao().isBefore(Instant.now()))
            return Either.left(Notification.create(new Error("The Cupom with codigo: " + aCupomCommand.codigo() + " has expired.")));

        if (cupom.getQuantidadeMaxima() != null && cupom.getUsosAtuais() != null
                && cupom.getUsosAtuais() >= cupom.getQuantidadeMaxima())
            return Either.left(Notification.create(new Error("The Cupom with codigo: " + aCupomCommand.codigo() + " has reached maximum usage.")));

        if (cupom.getValorMinimo() != null && aCupomCommand.valorPedido() != null
                && aCupomCommand.valorPedido() < cupom.getValorMinimo())
            return Either.left(Notification.create(new Error("The order value does not meet the minimum required for Cupom: " + aCupomCommand.codigo() + ".")));

        return Try(cupomDb::get)
                .toEither().bimap(Notification::create, CupomOutput::from);
    }
}
