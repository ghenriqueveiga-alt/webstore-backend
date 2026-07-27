package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.DeleteCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.DeleteCarrinhoOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;

public class DeleteCarrinhoUseCaseImpl extends DeleteCarrinhoUseCase {

    private final CarrinhoDomainGateway gateway;

    public DeleteCarrinhoUseCaseImpl(CarrinhoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCarrinhoOutput> execute(DeleteCarrinhoCommand aCarrinhoCommand) {

        Optional<Carrinho> carrinhoDb = gateway.readByUuid(CarrinhoUuid.from(aCarrinhoCommand.aUuid()));

        if (carrinhoDb.isPresent()) {
            gateway.delete(carrinhoDb.get());
            return Either.right(DeleteCarrinhoOutput.from(carrinhoDb.get()));
        }

        return Either.left(Notification.create(new Error("O Carrinho com uuid: " + aCarrinhoCommand.aUuid() + " nao foi encontrado.")));
    }
}
