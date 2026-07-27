package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.ReadAllCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.ReadAllCartaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllCartaoUseCaseImpl extends ReadAllCartaoUseCase {

    private final CartaoDomainGateway gateway;

    public ReadAllCartaoUseCaseImpl(CartaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCartaoOutput> execute(ReadAllCartaoCommand aCartaoCommand) {

        return Try(() -> gateway.readAll(aCartaoCommand.aSearchQuery()))
                .toEither().bimap(Notification::create, pagination -> ReadAllCartaoOutput.from(pagination.aContent()));
    }
}
