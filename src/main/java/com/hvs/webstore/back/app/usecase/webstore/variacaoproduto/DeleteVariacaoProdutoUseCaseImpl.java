package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.DeleteVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.DeleteVariacaoProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteVariacaoProdutoUseCaseImpl extends DeleteVariacaoProdutoUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public DeleteVariacaoProdutoUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteVariacaoProdutoOutput> execute(DeleteVariacaoProdutoCommand aVariacaoProdutoCommand) {

        Optional<VariacaoProduto> aVariacaoProdutoDB = aVariacaoProdutoCommand.aId() != null ?
                gateway.read(VariacaoProdutoId.from(aVariacaoProdutoCommand.aId())) : gateway.readByUuid(VariacaoProdutoUuid.from(aVariacaoProdutoCommand.aUuid()));

        if (aVariacaoProdutoDB.isEmpty())
            return Either.left(Notification.create(new Error("VariacaoProduto not found: " + (aVariacaoProdutoCommand.aId() != null ?
                    aVariacaoProdutoCommand.aId() : aVariacaoProdutoCommand.aUuid()))));

        return delete(aVariacaoProdutoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteVariacaoProdutoOutput> delete(VariacaoProduto aVariacaoProduto) {

        return Try(() -> {
            gateway.delete(aVariacaoProduto);
            return DeleteVariacaoProdutoOutput.from(aVariacaoProduto);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
