package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.DeleteProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.DeleteProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteProdutoUseCaseImpl extends DeleteProdutoUseCase {

    private final ProdutoDomainGateway gateway;

    public DeleteProdutoUseCaseImpl(ProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteProdutoOutput> execute(DeleteProdutoCommand aProdutoCommand) {

        Optional<Produto> aProdutoDB = aProdutoCommand.aId() != null ?
                gateway.read(ProdutoId.from(aProdutoCommand.aId())) : gateway.readByUuid(ProdutoUuid.from(aProdutoCommand.aUuid()));

        if (aProdutoDB.isEmpty())
            return Either.left(Notification.create(new Error("Produto not found: " + (aProdutoCommand.aId() != null ?
                    aProdutoCommand.aId() : aProdutoCommand.aUuid()))));

        return delete(aProdutoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteProdutoOutput> delete(Produto aProduto) {

        return Try(() -> {
            gateway.delete(aProduto);
            return DeleteProdutoOutput.from(aProduto);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
