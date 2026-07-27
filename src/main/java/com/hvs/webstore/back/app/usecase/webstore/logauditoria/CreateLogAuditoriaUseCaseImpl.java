package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.CreateLogAuditoriaCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.CreateLogAuditoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateLogAuditoriaUseCaseImpl extends CreateLogAuditoriaUseCase {

    private final LogAuditoriaDomainGateway gateway;

    public CreateLogAuditoriaUseCaseImpl(LogAuditoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateLogAuditoriaOutput> execute(CreateLogAuditoriaCommand aLogAuditoriaCommand) {

        var notification = Notification.create();
        var log = LogAuditoria.create(aLogAuditoriaCommand.aEntidadeNome(),
                                      aLogAuditoriaCommand.aEntidadeId(),
                                      aLogAuditoriaCommand.aAcaoDesc(),
                                      aLogAuditoriaCommand.aValorAntigoId(),
                                      aLogAuditoriaCommand.aValorNovoId(),
                                      aLogAuditoriaCommand.aUsuarioId());
        log.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(log);
    }

    @Transactional
    private Either<Notification, CreateLogAuditoriaOutput> create(LogAuditoria aLogAuditoria) {

        return Try(() -> gateway.create(aLogAuditoria))
                .toEither().bimap(Notification::create, CreateLogAuditoriaOutput::from);
    }
}
