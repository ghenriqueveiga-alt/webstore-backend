package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.app.command.webstore.carrinho.ClearCarrinhoCommand;
import com.hvs.webstore.back.app.output.webstore.carrinho.CarrinhoOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;

public class ClearCarrinhoUseCaseImpl extends ClearCarrinhoUseCase {

    private final CarrinhoDomainGateway carrinhoDomainGateway;

    public ClearCarrinhoUseCaseImpl(CarrinhoDomainGateway carrinhoDomainGateway) {

        this.carrinhoDomainGateway = carrinhoDomainGateway;
    }

    @Override
    public Either<Notification, CarrinhoOutput> execute(ClearCarrinhoCommand aClearCarrinhoCommand) {

        Optional<Carrinho> carrinhoDb = carrinhoDomainGateway.readByUuid(CarrinhoUuid.from(aClearCarrinhoCommand.aCarrinhoUuid()));

        if (carrinhoDb.isPresent()) {
            var carrinhoLimpo = carrinhoDb.get().limpar();
            var saved = carrinhoDomainGateway.update(carrinhoLimpo);
            return Either.right(CarrinhoOutput.from(saved));
        } else {
            return Either.left(Notification.create(new Error("O Carrinho com uuid: " + aClearCarrinhoCommand.aCarrinhoUuid() + " nao foi encontrado.")));
        }
    }
}
