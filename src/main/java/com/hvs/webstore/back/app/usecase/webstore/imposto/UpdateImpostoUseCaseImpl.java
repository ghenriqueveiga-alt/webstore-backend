package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.UpdateImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.UpdateImpostoOutput;
import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoId;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateImpostoUseCaseImpl extends UpdateImpostoUseCase {

    private final ImpostoDomainGateway gateway;

    public UpdateImpostoUseCaseImpl(ImpostoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateImpostoOutput> execute(UpdateImpostoCommand aImpostoCommand) {

        Optional<Imposto> aImpostoDB = aImpostoCommand.aId() != null ?
                gateway.read(ImpostoId.from(aImpostoCommand.aId())) : gateway.readByUuid(ImpostoUuid.from(aImpostoCommand.aUuid()));

        if (aImpostoDB.isEmpty())
            return Left(Notification.create(new Error("Imposto not found: " + (aImpostoCommand.aId() != null ?
                    aImpostoCommand.aId() : aImpostoCommand.aUuid()))));

        var notification = Notification.create();
        var imposto = Imposto.update(aImpostoDB.get().getId().getValue(),
                                     aImpostoDB.get().getUuid().getValue(),
                                     aImpostoCommand.aStatusCode(),
                                     aImpostoCommand.aNome(),
                                     aImpostoCommand.aTipoCode(),
                                     aImpostoCommand.aAliquota(),
                                     aImpostoCommand.aDescricao());
        imposto.validate(notification);

        return notification.hasError() ? Left(notification) : update(imposto);
    }

    @Transactional
    private Either<Notification, UpdateImpostoOutput> update(Imposto aImposto) {

        return Try(() -> gateway.update(aImposto))
                .toEither().bimap(Notification::create, UpdateImpostoOutput::from);
    }
}
