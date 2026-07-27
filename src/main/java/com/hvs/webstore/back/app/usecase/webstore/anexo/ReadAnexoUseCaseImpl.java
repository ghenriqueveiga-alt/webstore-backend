package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.ReadAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.ReadAnexoOutput;
import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoId;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadAnexoUseCaseImpl extends ReadAnexoUseCase {

    private final AnexoDomainGateway gateway;

    public ReadAnexoUseCaseImpl(AnexoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAnexoOutput> execute(ReadAnexoCommand aAnexoCommand) {

        if (aAnexoCommand.aEntidadeNome() != null) {
            var list = gateway.readByEntidade(aAnexoCommand.aEntidadeNome());

            if (list.isEmpty())
                return Either.left(Notification.create(new Error("Anexo not found: " + aAnexoCommand.aEntidadeNome())));

            return Try(list::getFirst).toEither().bimap(Notification::create, ReadAnexoOutput::from);
        }

        Optional<Anexo> aAnexoDB = aAnexoCommand.aId() != null ?
                gateway.read(AnexoId.from(aAnexoCommand.aId())) : gateway.readByUuid(AnexoUuid.from(aAnexoCommand.aUuid()));

        if (aAnexoDB.isPresent())
            return Try(aAnexoDB::get).toEither().bimap(Notification::create, ReadAnexoOutput::from);

        var aAnexoId = aAnexoCommand.aId() != null ? String.valueOf(aAnexoCommand.aId()) : aAnexoCommand.aUuid();

        return Either.left(Notification.create(new Error("Anexo not found: " + aAnexoId)));
    }
}
