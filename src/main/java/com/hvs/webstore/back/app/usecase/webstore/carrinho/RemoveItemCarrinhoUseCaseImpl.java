package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.RemoveItemCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CarrinhoOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;

public class RemoveItemCarrinhoUseCaseImpl extends RemoveItemCarrinhoUseCase {

    private final CarrinhoDomainGateway carrinhoDomainGateway;

    public RemoveItemCarrinhoUseCaseImpl(CarrinhoDomainGateway carrinhoDomainGateway) {

        this.carrinhoDomainGateway = carrinhoDomainGateway;
    }

    @Override
    public Either<Notification, CarrinhoOutput> execute(RemoveItemCarrinhoCommand aRemoveItemCarrinhoCommand) {

        Optional<Carrinho> carrinhoDb = carrinhoDomainGateway.readByUuid(CarrinhoUuid.from(aRemoveItemCarrinhoCommand.aCarrinhoUuid()));

        if (carrinhoDb.isPresent()) {
            var carrinhoAtualizado = carrinhoDb.get().removerItem(aRemoveItemCarrinhoCommand.aItemUuid());
            var saved = carrinhoDomainGateway.update(carrinhoAtualizado);
            return Either.right(CarrinhoOutput.from(saved));
        } else {
            return Either.left(Notification.create(new Error("O Carrinho com uuid: " + aRemoveItemCarrinhoCommand.aCarrinhoUuid() + " nao foi encontrado.")));
        }
    }
}
