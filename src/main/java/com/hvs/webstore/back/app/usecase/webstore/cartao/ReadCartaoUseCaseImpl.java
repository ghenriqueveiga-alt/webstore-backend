package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.ReadCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.ReadCartaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoId;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCartaoUseCaseImpl extends ReadCartaoUseCase {

    private final CartaoDomainGateway gateway;

    public ReadCartaoUseCaseImpl(CartaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCartaoOutput> execute(ReadCartaoCommand aCartaoCommand) {

        Optional<Cartao> aCartaoDB = aCartaoCommand.aId() != null ?
                gateway.read(CartaoId.from(aCartaoCommand.aId())) : gateway.readByUuid(CartaoUuid.from(aCartaoCommand.aUuid()));

        if (aCartaoDB.isPresent())
            return Try(aCartaoDB::get).toEither().bimap(Notification::create, ReadCartaoOutput::from);

        var aCartaoId = aCartaoCommand.aId() != null ? String.valueOf(aCartaoCommand.aId()) : aCartaoCommand.aUuid();

        return Either.left(Notification.create(new Error("Cartao not found: " + aCartaoId)));
    }
}
