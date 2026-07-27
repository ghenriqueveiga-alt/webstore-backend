package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.CreateNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.CreateNotificacaoTemplateOutput;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.TipoNotificacaoTemplate;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateNotificacaoTemplateUseCaseImpl extends CreateNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public CreateNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateNotificacaoTemplateOutput> execute(CreateNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        var notification = Notification.create();
        var tipo = aNotificacaoTemplateCommand.aTipoCode() != null ? TipoNotificacaoTemplate.findByCode(aNotificacaoTemplateCommand.aTipoCode()) : null;
        var notificacaoTemplate = NotificacaoTemplate.create(aNotificacaoTemplateCommand.aNome(),
                                                             tipo,
                                                             aNotificacaoTemplateCommand.aAssunto(),
                                                             aNotificacaoTemplateCommand.aCorpo(),
                                                             aNotificacaoTemplateCommand.aVariaveis());
        notificacaoTemplate.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(notificacaoTemplate);
    }

    @Transactional
    private Either<Notification, CreateNotificacaoTemplateOutput> create(NotificacaoTemplate aNotificacaoTemplate) {

        return Try(() -> gateway.create(aNotificacaoTemplate))
                .toEither().bimap(Notification::create, CreateNotificacaoTemplateOutput::from);
    }
}
