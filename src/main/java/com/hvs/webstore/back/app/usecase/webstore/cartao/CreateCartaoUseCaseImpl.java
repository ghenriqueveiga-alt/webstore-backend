package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.CreateCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.CreateCartaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateCartaoUseCaseImpl extends CreateCartaoUseCase {

    private final CartaoDomainGateway gateway;

    public CreateCartaoUseCaseImpl(CartaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCartaoOutput> execute(CreateCartaoCommand aCartaoCommand) {

        var notification = Notification.create();
        var cartao = Cartao.create(aCartaoCommand.aNomeTitular(),
                                   aCartaoCommand.aNumero(),
                                   aCartaoCommand.aBandeira(),
                                   aCartaoCommand.aTipo(),
                                   aCartaoCommand.aMesVencimento(),
                                   aCartaoCommand.aAnoVencimento(),
                                   aCartaoCommand.aCvv(),
                                   null);
        cartao.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(cartao);
    }

    @Transactional
    private Either<Notification, CreateCartaoOutput> create(Cartao aCartao) {

        return Try(() -> gateway.create(aCartao))
                .toEither().bimap(Notification::create, CreateCartaoOutput::from);
    }
}
