package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.CreateCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CreateCarrinhoOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public class CreateCarrinhoUseCaseImpl extends CreateCarrinhoUseCase {

    private final CarrinhoDomainGateway gateway;

    public CreateCarrinhoUseCaseImpl(CarrinhoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCarrinhoOutput> execute(CreateCarrinhoCommand aCarrinhoCommand) {

        var notification = Notification.create();
        var carrinho = Carrinho.create(aCarrinhoCommand.aUsuarioId());
        carrinho.validate(notification);

        if (notification.hasError())
            return Either.left(notification);

        var created = gateway.create(carrinho);

        return Either.right(CreateCarrinhoOutput.from(created));
    }
}
