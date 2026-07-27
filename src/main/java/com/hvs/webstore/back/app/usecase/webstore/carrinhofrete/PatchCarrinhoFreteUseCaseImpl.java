package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.PatchCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.PatchCarrinhoFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteId;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchCarrinhoFreteUseCaseImpl extends PatchCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public PatchCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCarrinhoFreteOutput> execute(PatchCarrinhoFreteCommand aCarrinhoFreteCommand) {

        Optional<CarrinhoFrete> aCarrinhoFreteDB = gateway.readByUuid(CarrinhoFreteUuid.from(aCarrinhoFreteCommand.aUuid()));

        if (aCarrinhoFreteDB.isEmpty())
            return Either.left(Notification.create(new Error("CarrinhoFrete not found: " + aCarrinhoFreteCommand.aUuid())));

        var notification = Notification.create();
        var cf = CarrinhoFrete.patch(aCarrinhoFreteCommand.aStatusCode(),
                                     aCarrinhoFreteCommand.aCarrinhoId(),
                                     aCarrinhoFreteCommand.aFreteId(),
                                     aCarrinhoFreteCommand.aValor(),
                                     aCarrinhoFreteCommand.aPrazo(),
                                     aCarrinhoFreteCommand.aTransportadoraId(),
                                     aCarrinhoFreteDB.get());
        cf.validate(notification);

        return notification.hasError() ? Left(notification) : patch(cf);
    }

    @Transactional
    private Either<Notification, PatchCarrinhoFreteOutput> patch(CarrinhoFrete aCarrinhoFrete) {

        return Try(() -> gateway.patch(aCarrinhoFrete)).toEither().bimap(Notification::create, PatchCarrinhoFreteOutput::from);
    }
}
