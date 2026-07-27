package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.ReadNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.ReadNotificacaoTemplateOutput;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateId;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadNotificacaoTemplateUseCaseImpl extends ReadNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public ReadNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadNotificacaoTemplateOutput> execute(ReadNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        Optional<NotificacaoTemplate> aNotificacaoTemplateDB = aNotificacaoTemplateCommand.aId() != null ?
                gateway.read(NotificacaoTemplateId.from(aNotificacaoTemplateCommand.aId())) : gateway.readByUuid(NotificacaoTemplateUuid.from(aNotificacaoTemplateCommand.aUuid()));

        if (aNotificacaoTemplateDB.isPresent())
            return Try(aNotificacaoTemplateDB::get).toEither().bimap(Notification::create, ReadNotificacaoTemplateOutput::from);

        var aNotificacaoTemplateId = aNotificacaoTemplateCommand.aId() != null ?
                String.valueOf(aNotificacaoTemplateCommand.aId()) : aNotificacaoTemplateCommand.aUuid();

        return Either.left(Notification.create(new Error("NotificacaoTemplate not found: " + aNotificacaoTemplateId)));
    }
}
