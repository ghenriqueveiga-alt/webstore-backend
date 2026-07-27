package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.CreatePrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.CreatePrecoPromocionalOutput;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreatePrecoPromocionalUseCaseImpl extends CreatePrecoPromocionalUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public CreatePrecoPromocionalUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreatePrecoPromocionalOutput> execute(CreatePrecoPromocionalCommand aPrecoPromocionalCommand) {

        var notification = Notification.create();
        var precoPromocional = PrecoPromocional.create(aPrecoPromocionalCommand.aProdutoId(),
                                                       aPrecoPromocionalCommand.aPrecoPromocional(),
                                                       aPrecoPromocionalCommand.aDataInicio(),
                                                       aPrecoPromocionalCommand.aDataFim());
        precoPromocional.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(precoPromocional);
    }

    @Transactional
    private Either<Notification, CreatePrecoPromocionalOutput> create(PrecoPromocional aPrecoPromocional) {

        return Try(() -> gateway.create(aPrecoPromocional))
                .toEither().bimap(Notification::create, CreatePrecoPromocionalOutput::from);
    }
}
