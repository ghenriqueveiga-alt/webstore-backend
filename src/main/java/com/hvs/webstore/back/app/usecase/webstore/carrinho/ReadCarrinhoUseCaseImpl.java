package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.ReadCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CarrinhoOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;

public class ReadCarrinhoUseCaseImpl extends ReadCarrinhoUseCase {

    private final CarrinhoDomainGateway gateway;

    public ReadCarrinhoUseCaseImpl(CarrinhoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CarrinhoOutput> execute(ReadCarrinhoCommand aCarrinhoCommand) {

        Optional<Carrinho> carrinhoDb = gateway.readByUuid(CarrinhoUuid.from(aCarrinhoCommand.aUuid()));

        if (carrinhoDb.isPresent())
            return Either.right(CarrinhoOutput.from(carrinhoDb.get()));

        return Either.left(Notification.create(new Error("O Carrinho com uuid: " + aCarrinhoCommand.aUuid() + " nao foi encontrado.")));
    }
}
