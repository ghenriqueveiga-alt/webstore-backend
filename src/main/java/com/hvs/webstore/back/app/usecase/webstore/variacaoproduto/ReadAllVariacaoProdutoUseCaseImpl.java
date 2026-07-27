package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.ReadAllVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.ReadAllVariacaoProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllVariacaoProdutoUseCaseImpl extends ReadAllVariacaoProdutoUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public ReadAllVariacaoProdutoUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllVariacaoProdutoOutput> execute(ReadAllVariacaoProdutoCommand aVariacaoProdutoCommand) {

        return Try(() -> gateway.readAll(aVariacaoProdutoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllVariacaoProdutoOutput::from);
    }
}
