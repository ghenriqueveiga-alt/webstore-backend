package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.DeleteImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.DeleteImpostoOutput;
import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoId;
import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteImpostoUseCaseImpl extends DeleteImpostoUseCase {

    private final ImpostoDomainGateway gateway;

    public DeleteImpostoUseCaseImpl(ImpostoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteImpostoOutput> execute(DeleteImpostoCommand aImpostoCommand) {

        Optional<Imposto> aImpostoDB = aImpostoCommand.aId() != null ?
                gateway.read(ImpostoId.from(aImpostoCommand.aId())) : gateway.readByUuid(ImpostoUuid.from(aImpostoCommand.aUuid()));

        if (aImpostoDB.isEmpty())
            return Either.left(Notification.create(new Error("Imposto not found: " + (aImpostoCommand.aId() != null ?
                    aImpostoCommand.aId() : aImpostoCommand.aUuid()))));

        return delete(aImpostoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteImpostoOutput> delete(Imposto aImposto) {

        return Try(() -> {
            gateway.delete(aImposto);
            return DeleteImpostoOutput.from(aImposto);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
