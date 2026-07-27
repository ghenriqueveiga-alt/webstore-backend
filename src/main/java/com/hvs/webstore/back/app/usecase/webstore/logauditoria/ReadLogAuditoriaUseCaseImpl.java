package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadLogAuditoriaCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadLogAuditoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaId;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadLogAuditoriaUseCaseImpl extends ReadLogAuditoriaUseCase {

    private final LogAuditoriaDomainGateway gateway;

    public ReadLogAuditoriaUseCaseImpl(LogAuditoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadLogAuditoriaOutput> execute(ReadLogAuditoriaCommand aLogAuditoriaCommand) {

        Optional<LogAuditoria> aLogAuditoriaDB = aLogAuditoriaCommand.aId() != null ?
                gateway.read(LogAuditoriaId.from(aLogAuditoriaCommand.aId())) : gateway.readByUuid(LogAuditoriaUuid.from(aLogAuditoriaCommand.aUuid()));

        if (aLogAuditoriaDB.isPresent())
            return Try(aLogAuditoriaDB::get)
                    .toEither().bimap(Notification::create, ReadLogAuditoriaOutput::from);

        var aLogAuditoriaId = aLogAuditoriaCommand.aId() != null ? String.valueOf(aLogAuditoriaCommand.aId()) : aLogAuditoriaCommand.aUuid();

        return Either.left(Notification.create(new Error("LogAuditoria not found: " + aLogAuditoriaId)));
    }
}
