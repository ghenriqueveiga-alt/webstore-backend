package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.ReadVariacaoProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.variacaoproduto.ReadVariacaoProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadVariacaoProdutoUseCaseImpl extends ReadVariacaoProdutoUseCase {

    private final VariacaoProdutoDomainGateway gateway;

    public ReadVariacaoProdutoUseCaseImpl(VariacaoProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadVariacaoProdutoOutput> execute(ReadVariacaoProdutoCommand aVariacaoProdutoCommand) {

        Optional<VariacaoProduto> aVariacaoProdutoDB = aVariacaoProdutoCommand.aId() != null ?
                gateway.read(VariacaoProdutoId.from(aVariacaoProdutoCommand.aId())) : gateway.readByUuid(VariacaoProdutoUuid.from(aVariacaoProdutoCommand.aUuid()));

        if (aVariacaoProdutoDB.isPresent())
            return Try(aVariacaoProdutoDB::get).toEither().bimap(Notification::create, ReadVariacaoProdutoOutput::from);

        var aVariacaoProdutoId = aVariacaoProdutoCommand.aId() != null ? String.valueOf(aVariacaoProdutoCommand.aId()) : aVariacaoProdutoCommand.aUuid();

        return Either.left(Notification.create(new Error("VariacaoProduto not found: " + aVariacaoProdutoId)));
    }
}
