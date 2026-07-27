package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.ReadVariacaoProdutoByProdutoIdCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.ReadAllVariacaoProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadVariacaoProdutoByProdutoIdUseCaseImpl extends ReadVariacaoProdutoByProdutoIdUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public ReadVariacaoProdutoByProdutoIdUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllVariacaoProdutoOutput> execute(ReadVariacaoProdutoByProdutoIdCommand aVariacaoProdutoCommand) {

        return Try(() -> gateway.readByProdutoId(aVariacaoProdutoCommand.aProdutoId()))
                .toEither().bimap(Notification::create, ReadAllVariacaoProdutoOutput::from);
    }
}
