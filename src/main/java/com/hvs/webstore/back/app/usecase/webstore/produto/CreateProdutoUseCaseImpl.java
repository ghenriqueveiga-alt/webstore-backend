package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.CreateProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.CreateProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateProdutoUseCaseImpl extends CreateProdutoUseCase {

    private final ProdutoDomainGateway gateway;

    public CreateProdutoUseCaseImpl(ProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateProdutoOutput> execute(CreateProdutoCommand aProdutoCommand) {

        var notification = Notification.create();
        var produto = Produto.create(aProdutoCommand.aNome(),
                                     aProdutoCommand.aDescricao(),
                                     aProdutoCommand.aPrecoId(),
                                     aProdutoCommand.aCaracteristicaIds(),
                                     aProdutoCommand.aImagenIds(),
                                     aProdutoCommand.aVideoIds(),
                                     aProdutoCommand.aCategoriaId(),
                                     aProdutoCommand.aMarcaId());
        produto.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(produto);
    }

    @Transactional
    private Either<Notification, CreateProdutoOutput> create(Produto aProduto) {

        return Try(() -> gateway.create(aProduto))
                .toEither().bimap(Notification::create, CreateProdutoOutput::from);
    }
}
