package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadLogAuditoriaByUsuarioIdCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadLogAuditoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadLogAuditoriaByUsuarioIdUseCaseImpl extends ReadLogAuditoriaByUsuarioIdUseCase {

    private final LogAuditoriaDomainGateway gateway;

    public ReadLogAuditoriaByUsuarioIdUseCaseImpl(LogAuditoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, List<ReadLogAuditoriaOutput>> execute(ReadLogAuditoriaByUsuarioIdCommand aLogAuditoriaCommand) {

        List<LogAuditoria> lista = gateway.readByUsuarioId(aLogAuditoriaCommand.aUsuarioId());

        if (!lista.isEmpty())
            return Either.right(lista.stream().map(ReadLogAuditoriaOutput::from).toList());

        return Either.left(Notification.create(new Error("No LogAuditoria found for usuarioId: " + aLogAuditoriaCommand.aUsuarioId())));
    }
}
