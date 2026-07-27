package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.UpdateCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.UpdateCartaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoId;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateCartaoUseCaseImpl extends UpdateCartaoUseCase {

    private final CartaoDomainGateway gateway;

    public UpdateCartaoUseCaseImpl(CartaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCartaoOutput> execute(UpdateCartaoCommand aCartaoCommand) {

        Optional<Cartao> aCartaoDB = aCartaoCommand.aId() != null ?
                gateway.read(CartaoId.from(aCartaoCommand.aId())) : gateway.readByUuid(CartaoUuid.from(aCartaoCommand.aUuid()));

        if (aCartaoDB.isEmpty())
            return Either.left(Notification.create(new Error("Cartao not found: " + (aCartaoCommand.aId() != null ?
                    aCartaoCommand.aId() : aCartaoCommand.aUuid()))));

        var notification = Notification.create();
        var cartao = Cartao.update(aCartaoDB.get().getId().getValue(),
                                   aCartaoDB.get().getUuid().getValue(),
                                   aCartaoDB.get().getStatusCode().getCode(),
                                   aCartaoCommand.aNomeTitular(),
                                   aCartaoCommand.aNumero(),
                                   aCartaoCommand.aBandeira(),
                                   aCartaoCommand.aTipo(),
                                   aCartaoCommand.aMesVencimento(),
                                   aCartaoCommand.aAnoVencimento(),
                                   aCartaoCommand.aCvv(),
                                   null);
        cartao.validate(notification);

        return notification.hasError() ? Left(notification) : update(cartao);
    }

    @Transactional
    private Either<Notification, UpdateCartaoOutput> update(Cartao aCartao) {

        return Try(() -> gateway.update(aCartao))
                .toEither().bimap(Notification::create, UpdateCartaoOutput::from);
    }
}
