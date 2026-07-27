package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.CreateVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.CreateVariacaoProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateVariacaoProdutoUseCaseImpl extends CreateVariacaoProdutoUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public CreateVariacaoProdutoUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateVariacaoProdutoOutput> execute(CreateVariacaoProdutoCommand aVariacaoProdutoCommand) {

        var notification = Notification.create();
        var variacaoProduto = VariacaoProduto.create(aVariacaoProdutoCommand.aProdutoId(),
                                                     aVariacaoProdutoCommand.aNome(),
                                                     aVariacaoProdutoCommand.aValor(),
                                                     aVariacaoProdutoCommand.aSku(),
                                                     aVariacaoProdutoCommand.aEstoque());
        variacaoProduto.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(variacaoProduto);
    }

    @Transactional
    private Either<Notification, CreateVariacaoProdutoOutput> create(VariacaoProduto aVariacaoProduto) {

        return Try(() -> gateway.create(aVariacaoProduto))
                .toEither().bimap(Notification::create, CreateVariacaoProdutoOutput::from);
    }
}
