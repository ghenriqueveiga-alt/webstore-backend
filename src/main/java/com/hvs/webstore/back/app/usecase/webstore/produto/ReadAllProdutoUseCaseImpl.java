package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.ReadAllProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.ReadAllProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllProdutoUseCaseImpl extends ReadAllProdutoUseCase {

    private final ProdutoDomainGateway gateway;

    public ReadAllProdutoUseCaseImpl(ProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllProdutoOutput> execute(ReadAllProdutoCommand aProdutoCommand) {

        return Try(() -> gateway.readAll(aProdutoCommand.aSearchQuery()))
                .toEither().bimap(Notification::create, pagination -> ReadAllProdutoOutput.from(pagination.aContent()));
    }
}
