package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.DeleteNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.DeleteNotificacaoTemplateOutput;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateId;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;

import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteNotificacaoTemplateUseCaseImpl extends DeleteNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public DeleteNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteNotificacaoTemplateOutput> execute(DeleteNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        Optional<NotificacaoTemplate> aNotificacaoTemplateDB = aNotificacaoTemplateCommand.aId() != null ?
                gateway.read(NotificacaoTemplateId.from(aNotificacaoTemplateCommand.aId())) : gateway.readByUuid(NotificacaoTemplateUuid.from(aNotificacaoTemplateCommand.aUuid()));

        if (aNotificacaoTemplateDB.isEmpty())
            return Either.left(Notification.create(new Error("NotificacaoTemplate not found: " + (aNotificacaoTemplateCommand.aId() != null ?
                    aNotificacaoTemplateCommand.aId() : aNotificacaoTemplateCommand.aUuid()))));

        return delete(aNotificacaoTemplateDB.get());
    }

    @Transactional
    private Either<Notification, DeleteNotificacaoTemplateOutput> delete(NotificacaoTemplate aNotificacaoTemplate) {

        return Try(() -> {
            gateway.delete(aNotificacaoTemplate);
            return DeleteNotificacaoTemplateOutput.from(aNotificacaoTemplate);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
