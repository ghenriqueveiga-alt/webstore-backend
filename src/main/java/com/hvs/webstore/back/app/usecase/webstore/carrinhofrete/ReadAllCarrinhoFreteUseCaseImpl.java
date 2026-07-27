package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.ReadAllCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.ReadAllCarrinhoFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllCarrinhoFreteUseCaseImpl extends ReadAllCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public ReadAllCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCarrinhoFreteOutput> execute(ReadAllCarrinhoFreteCommand aCarrinhoFreteCommand) {

        return Try(() -> gateway.readAll(aCarrinhoFreteCommand.aSearchQuery()))
                .toEither().bimap(Notification::create, ReadAllCarrinhoFreteOutput::from);
    }
}
