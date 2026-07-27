package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.UpdateCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.UpdateCarrinhoFreteOutput;
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

public class UpdateCarrinhoFreteUseCaseImpl extends UpdateCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public UpdateCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCarrinhoFreteOutput> execute(UpdateCarrinhoFreteCommand aCarrinhoFreteCommand) {

        Optional<CarrinhoFrete> aCarrinhoFreteDB = aCarrinhoFreteCommand.aId() != null ?
                gateway.read(CarrinhoFreteId.from(aCarrinhoFreteCommand.aId())) : gateway.readByUuid(CarrinhoFreteUuid.from(aCarrinhoFreteCommand.aUuid()));

        if (aCarrinhoFreteDB.isEmpty())
            return Either.left(Notification.create(new Error("CarrinhoFrete not found: " + (aCarrinhoFreteCommand.aId() != null ?
                    aCarrinhoFreteCommand.aId() : aCarrinhoFreteCommand.aUuid()))));

        var notification = Notification.create();
        var cf = CarrinhoFrete.update(aCarrinhoFreteCommand.aId(),
                                      aCarrinhoFreteCommand.aUuid(),
                                      aCarrinhoFreteCommand.aStatusCode(),
                                      aCarrinhoFreteCommand.aCarrinhoId(),
                                      aCarrinhoFreteCommand.aFreteId(),
                                      aCarrinhoFreteCommand.aValor(),
                                      aCarrinhoFreteCommand.aPrazo(),
                                      aCarrinhoFreteCommand.aTransportadoraId());
        cf.validate(notification);

        return notification.hasError() ? Left(notification) : update(cf);
    }

    @Transactional
    private Either<Notification, UpdateCarrinhoFreteOutput> update(CarrinhoFrete aCarrinhoFrete) {

        return Try(() -> gateway.update(aCarrinhoFrete))
                .toEither().bimap(Notification::create, UpdateCarrinhoFreteOutput::from);
    }
}
