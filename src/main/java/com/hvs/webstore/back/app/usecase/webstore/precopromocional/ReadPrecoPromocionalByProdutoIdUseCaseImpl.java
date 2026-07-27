package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.ReadPrecoPromocionalByProdutoIdCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.ReadAllPrecoPromocionalOutput;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadPrecoPromocionalByProdutoIdUseCaseImpl extends ReadPrecoPromocionalByProdutoIdUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public ReadPrecoPromocionalByProdutoIdUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPrecoPromocionalOutput> execute(ReadPrecoPromocionalByProdutoIdCommand aPrecoPromocionalCommand) {

        return Try(() -> gateway.readByProdutoId(aPrecoPromocionalCommand.aProdutoId()))
                .toEither().bimap(Notification::create, ReadAllPrecoPromocionalOutput::from);
    }
}
