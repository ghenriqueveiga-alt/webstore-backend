package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.CreateEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.CreateEstoqueOutput;
import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateEstoqueUseCaseImpl extends CreateEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public CreateEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateEstoqueOutput> execute(CreateEstoqueCommand aEstoqueCommand) {

        var notification = Notification.create();
        var estoque = Estoque.create(aEstoqueCommand.aProdutoId(),
                                     aEstoqueCommand.aQuantidade(),
                                     aEstoqueCommand.aQuantidadeMinima());
        estoque.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(estoque);
    }

    @Transactional
    private Either<Notification, CreateEstoqueOutput> create(Estoque aEstoque) {

        return Try(() -> gateway.create(aEstoque))
                .toEither().bimap(Notification::create, CreateEstoqueOutput::from);
    }
}
