package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadAllLogAuditoriaCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadAllLogAuditoriaOutput;
import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllLogAuditoriaUseCaseImpl extends ReadAllLogAuditoriaUseCase {

    private final LogAuditoriaDomainGateway gateway;

    public ReadAllLogAuditoriaUseCaseImpl(LogAuditoriaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllLogAuditoriaOutput> execute(ReadAllLogAuditoriaCommand aLogAuditoriaCommand) {

        Pagination<?> pagination = gateway.readAll(aLogAuditoriaCommand.aSearchQuery());

        if (!pagination.aContent().isEmpty())
            return Try(() -> gateway.readAll(aLogAuditoriaCommand.aSearchQuery())).toEither().bimap(
                    Notification::create, ReadAllLogAuditoriaOutput::from);

        return Either.left(Notification.create(new Error("No LogAuditoria was found.")));
    }
}
