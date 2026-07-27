package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.UpdateVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.UpdateVariacaoProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateVariacaoProdutoUseCaseImpl extends UpdateVariacaoProdutoUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public UpdateVariacaoProdutoUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateVariacaoProdutoOutput> execute(UpdateVariacaoProdutoCommand aVariacaoProdutoCommand) {

        Optional<VariacaoProduto> aVariacaoProdutoDB = aVariacaoProdutoCommand.aId() != null ?
                gateway.read(VariacaoProdutoId.from(aVariacaoProdutoCommand.aId())) : gateway.readByUuid(VariacaoProdutoUuid.from(aVariacaoProdutoCommand.aUuid()));

        if (aVariacaoProdutoDB.isEmpty())
            return Left(Notification.create(new Error("VariacaoProduto not found: " + (aVariacaoProdutoCommand.aId() != null ?
                    aVariacaoProdutoCommand.aId() : aVariacaoProdutoCommand.aUuid()))));

        var notification = Notification.create();
        var variacaoProduto = VariacaoProduto.update(aVariacaoProdutoDB.get().getId().getValue(),
                                                     aVariacaoProdutoDB.get().getUuid().getValue(),
                                                     aVariacaoProdutoCommand.aStatusCode(),
                                                     aVariacaoProdutoCommand.aProdutoId(),
                                                     aVariacaoProdutoCommand.aNome(),
                                                     aVariacaoProdutoCommand.aValor(),
                                                     aVariacaoProdutoCommand.aSku(),
                                                     aVariacaoProdutoCommand.aEstoque());
        variacaoProduto.validate(notification);

        return notification.hasError() ? Left(notification) : update(variacaoProduto);
    }

    @Transactional
    private Either<Notification, UpdateVariacaoProdutoOutput> update(VariacaoProduto aVariacaoProduto) {

        return Try(() -> gateway.update(aVariacaoProduto))
                .toEither().bimap(Notification::create, UpdateVariacaoProdutoOutput::from);
    }
}
