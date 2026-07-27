package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.app.command.webstore.produto.ReadProdutoCommand;
import com.hvs.webstore.back.app.output.webstore.produto.ReadProdutoOutput;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadProdutoUseCaseImpl extends ReadProdutoUseCase {

    private final ProdutoDomainGateway gateway;

    public ReadProdutoUseCaseImpl(ProdutoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadProdutoOutput> execute(ReadProdutoCommand aProdutoCommand) {

        Optional<Produto> aProdutoDB = aProdutoCommand.aId() != null ?
                gateway.read(ProdutoId.from(aProdutoCommand.aId())) : gateway.readByUuid(ProdutoUuid.from(aProdutoCommand.aUuid()));

        if (aProdutoDB.isPresent())
            return Try(aProdutoDB::get).toEither().bimap(Notification::create, ReadProdutoOutput::from);

        var aProdutoId = aProdutoCommand.aId() != null ? String.valueOf(aProdutoCommand.aId()) : aProdutoCommand.aUuid();

        return Either.left(Notification.create(new Error("Produto not found: " + aProdutoId)));
    }
}
