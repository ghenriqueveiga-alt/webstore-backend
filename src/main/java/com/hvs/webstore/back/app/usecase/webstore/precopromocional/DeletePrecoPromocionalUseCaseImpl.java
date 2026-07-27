package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.DeletePrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.DeletePrecoPromocionalOutput;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalId;
import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeletePrecoPromocionalUseCaseImpl extends DeletePrecoPromocionalUseCase {

    private final PrecoPromocionalDomainGateway gateway;

    public DeletePrecoPromocionalUseCaseImpl(PrecoPromocionalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeletePrecoPromocionalOutput> execute(DeletePrecoPromocionalCommand aPrecoPromocionalCommand) {

        Optional<PrecoPromocional> aPrecoPromocionalDB = aPrecoPromocionalCommand.aId() != null ?
                gateway.read(PrecoPromocionalId.from(aPrecoPromocionalCommand.aId())) : gateway.readByUuid(PrecoPromocionalUuid.from(aPrecoPromocionalCommand.aUuid()));

        if (aPrecoPromocionalDB.isEmpty())
            return Either.left(Notification.create(new Error("PrecoPromocional not found: " + (aPrecoPromocionalCommand.aId() != null ?
                    aPrecoPromocionalCommand.aId() : aPrecoPromocionalCommand.aUuid()))));

        return delete(aPrecoPromocionalDB.get());
    }

    @Transactional
    private Either<Notification, DeletePrecoPromocionalOutput> delete(PrecoPromocional aPrecoPromocional) {

        return Try(() -> {
            gateway.delete(aPrecoPromocional);
            return DeletePrecoPromocionalOutput.from(aPrecoPromocional);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
