package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.PatchAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.PatchAnexoOutput;
import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoId;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchAnexoUseCaseImpl extends PatchAnexoUseCase {

    private final AnexoDomainGateway gateway;

    public PatchAnexoUseCaseImpl(AnexoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchAnexoOutput> execute(PatchAnexoCommand aAnexoCommand) {

        Optional<Anexo> aAnexoDB = aAnexoCommand.aId() != null ?
                gateway.read(AnexoId.from(aAnexoCommand.aId())) : gateway.readByUuid(AnexoUuid.from(aAnexoCommand.aUuid()));

        if (aAnexoDB.isEmpty())
            return Either.left(Notification.create(new Error("Anexo not found: " + (aAnexoCommand.aId() != null ?
                    aAnexoCommand.aId() : aAnexoCommand.aUuid()))));

        var notification = Notification.create();
        var anexo = Anexo.patch(aAnexoCommand.aStatusCode(),
                                aAnexoCommand.aEntidadeNome(),
                                aAnexoCommand.aEntidadeId(),
                                aAnexoCommand.aNome(),
                                aAnexoCommand.aTipo(),
                                aAnexoCommand.aTamanho(),
                                aAnexoCommand.aUrl(),
                                aAnexoDB.get());
        anexo.validate(notification);

        return notification.hasError() ? Left(notification) : patch(anexo);
    }
    @Transactional
    private Either<Notification, PatchAnexoOutput> patch(Anexo aAnexo) {

        return Try(() -> gateway.patch(aAnexo)).toEither().bimap(Notification::create, PatchAnexoOutput::from);
    }
}
