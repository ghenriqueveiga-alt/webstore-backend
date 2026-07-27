package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.ReadByTipoNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.ReadByTipoNotificacaoTemplateOutput;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadByTipoNotificacaoTemplateUseCaseImpl extends ReadByTipoNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public ReadByTipoNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadByTipoNotificacaoTemplateOutput> execute(ReadByTipoNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        return Try(() -> gateway.readByTipo(aNotificacaoTemplateCommand.aTipoCode()))
                .toEither().bimap(Notification::create, ReadByTipoNotificacaoTemplateOutput::from);
    }
}
