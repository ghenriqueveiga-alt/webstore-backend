package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.PatchVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.PatchVariacaoProdutoOutput;
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

public class PatchVariacaoProdutoUseCaseImpl extends PatchVariacaoProdutoUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public PatchVariacaoProdutoUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchVariacaoProdutoOutput> execute(PatchVariacaoProdutoCommand aVariacaoProdutoCommand) {

        Optional<VariacaoProduto> aVariacaoProdutoDB = aVariacaoProdutoCommand.aId() != null ?
                gateway.read(VariacaoProdutoId.from(aVariacaoProdutoCommand.aId())) : gateway.readByUuid(VariacaoProdutoUuid.from(aVariacaoProdutoCommand.aUuid()));

        if (aVariacaoProdutoDB.isEmpty())
            return Left(Notification.create(new Error("VariacaoProduto not found: " + (aVariacaoProdutoCommand.aId() != null ?
                    aVariacaoProdutoCommand.aId() : aVariacaoProdutoCommand.aUuid()))));

        var notification = Notification.create();
        var variacaoProduto = VariacaoProduto.patch(aVariacaoProdutoCommand.aStatusCode(),
                                                    aVariacaoProdutoCommand.aProdutoId(),
                                                    aVariacaoProdutoCommand.aNome(),
                                                    aVariacaoProdutoCommand.aValor(),
                                                    aVariacaoProdutoCommand.aSku(),
                                                    aVariacaoProdutoCommand.aEstoque(),
                                                    aVariacaoProdutoDB.get());
        variacaoProduto.validate(notification);

        return notification.hasError() ? Left(notification) : patch(variacaoProduto);
    }

    @Transactional
    private Either<Notification, PatchVariacaoProdutoOutput> patch(VariacaoProduto aVariacaoProduto) {

        return Try(() -> gateway.patch(aVariacaoProduto))
                .toEither().bimap(Notification::create, PatchVariacaoProdutoOutput::from);
    }
}
