package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.DeleteCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.DeleteCartaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoId;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCartaoUseCaseImpl extends DeleteCartaoUseCase {

    private final CartaoDomainGateway gateway;

    public DeleteCartaoUseCaseImpl(CartaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCartaoOutput> execute(DeleteCartaoCommand aCartaoCommand) {

        Optional<Cartao> aCartaoDB = aCartaoCommand.aId() != null ?
                gateway.read(CartaoId.from(aCartaoCommand.aId())) : gateway.readByUuid(CartaoUuid.from(aCartaoCommand.aUuid()));

        if (aCartaoDB.isEmpty())
            return Either.left(Notification.create(new Error("Cartao not found: " + (aCartaoCommand.aId() != null ?
                    aCartaoCommand.aId() : aCartaoCommand.aUuid()))));

        return delete(aCartaoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteCartaoOutput> delete(Cartao aCartao) {

        return Try(() -> {
            gateway.delete(aCartao);
            return DeleteCartaoOutput.from(aCartao);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
