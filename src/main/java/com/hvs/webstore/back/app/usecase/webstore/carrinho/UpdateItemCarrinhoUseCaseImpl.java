package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.UpdateItemCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CarrinhoOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;

public class UpdateItemCarrinhoUseCaseImpl extends UpdateItemCarrinhoUseCase {

    private final CarrinhoDomainGateway carrinhoDomainGateway;

    public UpdateItemCarrinhoUseCaseImpl(CarrinhoDomainGateway carrinhoDomainGateway) {

        this.carrinhoDomainGateway = carrinhoDomainGateway;
    }

    @Override
    public Either<Notification, CarrinhoOutput> execute(UpdateItemCarrinhoCommand aUpdateItemCarrinhoCommand) {

        Optional<Carrinho> carrinhoDb = carrinhoDomainGateway.readByUuid(CarrinhoUuid.from(aUpdateItemCarrinhoCommand.aCarrinhoUuid()));

        if (carrinhoDb.isPresent()) {
            var carrinhoAtualizado = carrinhoDb.get().atualizarQuantidade(aUpdateItemCarrinhoCommand.aItemUuid(), aUpdateItemCarrinhoCommand.aQuantidade());
            var saved = carrinhoDomainGateway.update(carrinhoAtualizado);
            return Either.right(CarrinhoOutput.from(saved));
        } else {
            return Either.left(Notification.create(new Error("O Carrinho com uuid: " + aUpdateItemCarrinhoCommand.aCarrinhoUuid() + " nao foi encontrado.")));
        }
    }
}
