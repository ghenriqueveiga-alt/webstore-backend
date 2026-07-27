package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.CreateCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.CreateCarrinhoFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCarrinhoFreteUseCaseImpl extends CreateCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public CreateCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCarrinhoFreteOutput> execute(CreateCarrinhoFreteCommand aCarrinhoFreteCommand) {

        var notification = Notification.create();
        var cf = CarrinhoFrete.create(aCarrinhoFreteCommand.aCarrinhoId(),
                                      aCarrinhoFreteCommand.aFreteId(),
                                      aCarrinhoFreteCommand.aValor(),
                                      aCarrinhoFreteCommand.aPrazo(),
                                      aCarrinhoFreteCommand.aTransportadoraId());
        cf.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(cf);
    }

    @Transactional
    private Either<Notification, CreateCarrinhoFreteOutput> create(CarrinhoFrete aCarrinhoFrete) {

        return Try(() -> gateway.create(aCarrinhoFrete))
                .toEither().bimap(Notification::create, CreateCarrinhoFreteOutput::from);
    }
}
