package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.UpdateProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.UpdateProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateProdutoUseCaseImpl extends UpdateProdutoUseCase {

    private final ProdutoDomainGateway gateway;

    public UpdateProdutoUseCaseImpl(ProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateProdutoOutput> execute(UpdateProdutoCommand aProdutoCommand) {

        Optional<Produto> aProdutoDB = aProdutoCommand.aId() != null ?
                gateway.read(ProdutoId.from(aProdutoCommand.aId())) : gateway.readByUuid(ProdutoUuid.from(aProdutoCommand.aUuid()));

        if (aProdutoDB.isEmpty())
            return Left(Notification.create(new Error("Produto not found: " + (aProdutoCommand.aId() != null ?
                    aProdutoCommand.aId() : aProdutoCommand.aUuid()))));

        var notification = Notification.create();
        var produto = Produto.update(aProdutoDB.get().getId().getValue(),
                                     aProdutoDB.get().getUuid().getValue(),
                                     aProdutoCommand.aStatusCode(),
                                     aProdutoCommand.aNome(),
                                     aProdutoCommand.aDescricao(),
                                     aProdutoCommand.aPrecoId(),
                                     aProdutoCommand.aCaracteristicaIds(),
                                     aProdutoCommand.aImagemIds(),
                                     aProdutoCommand.aVideoIds(),
                                     aProdutoCommand.aCategoriaId(),
                                     aProdutoCommand.aMarcaId(),
                                     aProdutoCommand.aDataPublicacao());
        produto.validate(notification);

        return notification.hasError() ? Left(notification) : update(produto);
    }

    @Transactional
    private Either<Notification, UpdateProdutoOutput> update(Produto aProduto) {

        return Try(() -> gateway.update(aProduto))
                .toEither().bimap(Notification::create, UpdateProdutoOutput::from);
    }
}
