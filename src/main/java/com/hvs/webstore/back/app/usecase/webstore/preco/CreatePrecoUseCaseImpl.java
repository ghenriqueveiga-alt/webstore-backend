package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.CreatePrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.CreatePrecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreatePrecoUseCaseImpl extends CreatePrecoUseCase {

    private final PrecoDomainGateway gateway;

    public CreatePrecoUseCaseImpl(PrecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreatePrecoOutput> execute(CreatePrecoCommand aPrecoCommand) {

        var notification = Notification.create();
        var preco = Preco.create(aPrecoCommand.valor(),
                                 aPrecoCommand.tipoPagamento(),
                                 aPrecoCommand.qtdVezesParcelamento(),
                                 aPrecoCommand.valorParcela(),
                                 aPrecoCommand.valorTotalParcelamento(),
                                 null);
        preco.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(preco);
    }

    @Transactional
    private Either<Notification, CreatePrecoOutput> create(Preco aPreco) {

        return Try(() -> gateway.create(aPreco))
                .toEither().bimap(Notification::create, CreatePrecoOutput::from);
    }
}
