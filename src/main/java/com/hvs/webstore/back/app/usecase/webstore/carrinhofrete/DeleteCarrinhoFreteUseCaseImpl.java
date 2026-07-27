package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.DeleteCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.DeleteCarrinhoFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteId;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteCarrinhoFreteUseCaseImpl extends DeleteCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public DeleteCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteCarrinhoFreteOutput> execute(DeleteCarrinhoFreteCommand aCarrinhoFreteCommand) {

        Optional<CarrinhoFrete> aCarrinhoFreteDB = aCarrinhoFreteCommand.aId() != null ?
                gateway.read(CarrinhoFreteId.from(aCarrinhoFreteCommand.aId())) : gateway.readByUuid(CarrinhoFreteUuid.from(aCarrinhoFreteCommand.aUuid()));

        if (aCarrinhoFreteDB.isEmpty())
            return Either.left(Notification.create(new Error("CarrinhoFrete not found: " + (aCarrinhoFreteCommand.aId() != null ?
                    aCarrinhoFreteCommand.aId() : aCarrinhoFreteCommand.aUuid()))));

        return delete(aCarrinhoFreteDB.get());
    }

    @Transactional
    private Either<Notification, DeleteCarrinhoFreteOutput> delete(CarrinhoFrete aCarrinhoFrete) {

        return Try(() -> {
            gateway.delete(aCarrinhoFrete);
            return DeleteCarrinhoFreteOutput.from(aCarrinhoFrete);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
