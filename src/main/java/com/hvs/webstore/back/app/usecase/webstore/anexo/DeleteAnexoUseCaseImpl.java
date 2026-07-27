package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.DeleteAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.DeleteAnexoOutput;
import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoId;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteAnexoUseCaseImpl extends DeleteAnexoUseCase {

    private final AnexoDomainGateway gateway;

    public DeleteAnexoUseCaseImpl(AnexoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteAnexoOutput> execute(DeleteAnexoCommand aAnexoCommand) {

        Optional<Anexo> aAnexoDB = aAnexoCommand.aId() != null ?
                gateway.read(AnexoId.from(aAnexoCommand.aId())) : gateway.readByUuid(AnexoUuid.from(aAnexoCommand.aUuid()));

        if (aAnexoDB.isEmpty())
            return Either.left(Notification.create(new Error("Anexo not found: " + (aAnexoCommand.aId() != null ?
                    aAnexoCommand.aId() : aAnexoCommand.aUuid()))));

        return delete(aAnexoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteAnexoOutput> delete(Anexo aAnexo) {

        return Try(() -> {
            gateway.delete(aAnexo);

            return DeleteAnexoOutput.from(aAnexo);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
