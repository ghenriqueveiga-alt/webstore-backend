package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.ReadCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.ReadCarrinhoFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteId;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCarrinhoFreteUseCaseImpl extends ReadCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public ReadCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCarrinhoFreteOutput> execute(ReadCarrinhoFreteCommand aCarrinhoFreteCommand) {

        Optional<CarrinhoFrete> aCarrinhoFreteDB = aCarrinhoFreteCommand.aId() != null ?
                gateway.read(CarrinhoFreteId.from(aCarrinhoFreteCommand.aId())) : gateway.readByUuid(CarrinhoFreteUuid.from(aCarrinhoFreteCommand.aUuid()));

        if (aCarrinhoFreteDB.isPresent())
            return Try(aCarrinhoFreteDB::get).toEither().bimap(Notification::create, ReadCarrinhoFreteOutput::from);

        var aCarrinhoFreteId = aCarrinhoFreteCommand.aId() != null ? String.valueOf(aCarrinhoFreteCommand.aId()) : aCarrinhoFreteCommand.aUuid();

        return Either.left(Notification.create(new Error("CarrinhoFrete not found: " + aCarrinhoFreteId)));
    }
}
