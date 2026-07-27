package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.ReadByCarrinhoIdCarrinhoFreteCommand;
import com.hvs.webstore.back.app.output.webstore.carrinhofrete.ReadAllCarrinhoFreteOutput;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadByCarrinhoIdCarrinhoFreteUseCaseImpl extends ReadByCarrinhoIdCarrinhoFreteUseCase {

    private final CarrinhoFreteDomainGateway gateway;

    public ReadByCarrinhoIdCarrinhoFreteUseCaseImpl(CarrinhoFreteDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCarrinhoFreteOutput> execute(ReadByCarrinhoIdCarrinhoFreteCommand aReadByCarrinhoIdCarrinhoFreteCommand) {

        return Try(() -> {
            List<CarrinhoFrete> list = gateway.readByCarrinhoId(aReadByCarrinhoIdCarrinhoFreteCommand.aCarrinhoId());
            return ReadAllCarrinhoFreteOutput.from(list);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
