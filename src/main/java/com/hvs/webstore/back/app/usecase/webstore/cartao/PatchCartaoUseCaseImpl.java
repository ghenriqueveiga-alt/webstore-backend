package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.PatchCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.PatchCartaoOutput;
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

public class PatchCartaoUseCaseImpl extends PatchCartaoUseCase {

    private final CartaoDomainGateway gateway;

    public PatchCartaoUseCaseImpl(CartaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCartaoOutput> execute(PatchCartaoCommand aCartaoCommand) {

        Optional<Cartao> aCartaoDB = aCartaoCommand.aId() != null ?
                gateway.read(CartaoId.from(aCartaoCommand.aId())) : gateway.readByUuid(CartaoUuid.from(aCartaoCommand.aUuid()));

        if (aCartaoDB.isEmpty())
            return Either.left(Notification.create(new Error("Cartao not found: " + (aCartaoCommand.aId() != null ?
                    aCartaoCommand.aId() : aCartaoCommand.aUuid()))));

        var notification = Notification.create();
        var cartao = Cartao.patch(aCartaoCommand.aStatusCode(),
                                  aCartaoCommand.aNomeTitular(),
                                  aCartaoCommand.aNumero(),
                                  aCartaoCommand.aBandeira(),
                                  aCartaoCommand.aTipo(),
                                  aCartaoCommand.aMesVencimento(),
                                  aCartaoCommand.aAnoVencimento(),
                                  aCartaoCommand.aCvv(),
                                  null,
                                  aCartaoDB.get());
        cartao.validate(notification);

        return notification.hasError() ? Left(notification) : patch(cartao);
    }

    @Transactional
    private Either<Notification, PatchCartaoOutput> patch(Cartao aCartao) {

        return Try(() -> gateway.patch(aCartao))
                .toEither().bimap(Notification::create, PatchCartaoOutput::from);
    }
}
