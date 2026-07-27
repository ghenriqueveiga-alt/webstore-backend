package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.PatchProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.PatchProdutoOutput;
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

public class PatchProdutoUseCaseImpl extends PatchProdutoUseCase {

    private final ProdutoDomainGateway gateway;

    public PatchProdutoUseCaseImpl(ProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchProdutoOutput> execute(PatchProdutoCommand aProdutoCommand) {

        Optional<Produto> aProdutoDB = aProdutoCommand.aId() != null ?
                gateway.read(ProdutoId.from(aProdutoCommand.aId())) : gateway.readByUuid(ProdutoUuid.from(aProdutoCommand.aUuid()));

        if (aProdutoDB.isEmpty())
            return Left(Notification.create(new Error("Produto not found: " + (aProdutoCommand.aId() != null ?
                    aProdutoCommand.aId() : aProdutoCommand.aUuid()))));

        var notification = Notification.create();
        var produto = Produto.patch(aProdutoCommand.aStatusCode(),
                                    aProdutoCommand.aNome(),
                                    aProdutoCommand.aDescricao(),
                                    aProdutoCommand.aPrecoId(),
                                    aProdutoCommand.aMarcaId(),
                                    aProdutoCommand.aCaracteristicaIds(),
                                    aProdutoCommand.aImagemIds(),
                                    aProdutoCommand.aVideoIds(),
                                    aProdutoCommand.aCategoriaId(),
                                    aProdutoDB.get());
        produto.validate(notification);

        return notification.hasError() ? Left(notification) : patch(produto);
    }
    @Transactional
    private Either<Notification, PatchProdutoOutput> patch(Produto aProduto) {

        return Try(() -> gateway.patch(aProduto))
                .toEither().bimap(Notification::create, PatchProdutoOutput::from);
    }
}
