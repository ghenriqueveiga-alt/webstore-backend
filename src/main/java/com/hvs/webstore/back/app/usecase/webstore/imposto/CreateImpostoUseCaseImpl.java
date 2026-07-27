package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.CreateImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.CreateImpostoOutput;
import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateImpostoUseCaseImpl extends CreateImpostoUseCase {

    private final ImpostoDomainGateway gateway;

    public CreateImpostoUseCaseImpl(ImpostoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateImpostoOutput> execute(CreateImpostoCommand aImpostoCommand) {

        var notification = Notification.create();
        var imposto = Imposto.create(aImpostoCommand.aNome(),
                                     aImpostoCommand.aTipoCode(),
                                     aImpostoCommand.aAliquota(),
                                     aImpostoCommand.aDescricao());
        imposto.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(imposto);
    }

    @Transactional
    private Either<Notification, CreateImpostoOutput> create(Imposto aImposto) {

        return Try(() -> gateway.create(aImposto))
                .toEither().bimap(Notification::create, CreateImpostoOutput::from);
    }
}
