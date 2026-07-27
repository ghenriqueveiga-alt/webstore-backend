package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.PatchImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.PatchImpostoOutput;
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

public class PatchImpostoUseCaseImpl extends PatchImpostoUseCase {

    private final ImpostoDomainGateway gateway;

    public PatchImpostoUseCaseImpl(ImpostoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchImpostoOutput> execute(PatchImpostoCommand aImpostoCommand) {

        Optional<Imposto> aImpostoDB = aImpostoCommand.aId() != null ?
                gateway.read(ImpostoId.from(aImpostoCommand.aId())) : gateway.readByUuid(ImpostoUuid.from(aImpostoCommand.aUuid()));

        if (aImpostoDB.isEmpty())
            return Left(Notification.create(new Error("Imposto not found: " + (aImpostoCommand.aId() != null ?
                    aImpostoCommand.aId() : aImpostoCommand.aUuid()))));

        var notification = Notification.create();
        var imposto = Imposto.patch(aImpostoCommand.aStatusCode(),
                                    aImpostoCommand.aNome(),
                                    aImpostoCommand.aTipoCode(),
                                    aImpostoCommand.aAliquota(),
                                    aImpostoCommand.aDescricao(),
                                    aImpostoDB.get());
        imposto.validate(notification);

        return notification.hasError() ? Left(notification) : patch(imposto);
    }
    @Transactional
    private Either<Notification, PatchImpostoOutput> patch(Imposto aImposto) {

        return Try(() -> gateway.patch(aImposto))
                .toEither().bimap(Notification::create, PatchImpostoOutput::from);
    }
}
