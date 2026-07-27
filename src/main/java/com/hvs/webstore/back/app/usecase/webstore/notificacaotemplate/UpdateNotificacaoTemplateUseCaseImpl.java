package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.UpdateNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.UpdateNotificacaoTemplateOutput;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateId;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateUuid;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.TipoNotificacaoTemplate;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateNotificacaoTemplateUseCaseImpl extends UpdateNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public UpdateNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateNotificacaoTemplateOutput> execute(UpdateNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        Optional<NotificacaoTemplate> aNotificacaoTemplateDB = aNotificacaoTemplateCommand.aId() != null ?
                gateway.read(NotificacaoTemplateId.from(aNotificacaoTemplateCommand.aId())) : gateway.readByUuid(NotificacaoTemplateUuid.from(aNotificacaoTemplateCommand.aUuid()));

        if (aNotificacaoTemplateDB.isEmpty())
            return Left(Notification.create(new Error("NotificacaoTemplate not found: " + (aNotificacaoTemplateCommand.aId() != null ?
                    aNotificacaoTemplateCommand.aId() : aNotificacaoTemplateCommand.aUuid()))));

        var notification = Notification.create();
        var tipo = aNotificacaoTemplateCommand.aTipoCode() != null ? TipoNotificacaoTemplate.findByCode(aNotificacaoTemplateCommand.aTipoCode()) : null;
        var notificacaoTemplate = NotificacaoTemplate.update(aNotificacaoTemplateDB.get().getId().getValue(),
                                                             aNotificacaoTemplateDB.get().getUuid().getValue(),
                                                             aNotificacaoTemplateCommand.aStatusCode(),
                                                             aNotificacaoTemplateCommand.aNome(), tipo,
                                                             aNotificacaoTemplateCommand.aAssunto(),
                                                             aNotificacaoTemplateCommand.aCorpo(),
                                                             aNotificacaoTemplateCommand.aVariaveis());
        notificacaoTemplate.validate(notification);

        return notification.hasError() ? Left(notification) : update(notificacaoTemplate);
    }
    @Transactional
    private Either<Notification, UpdateNotificacaoTemplateOutput> update(NotificacaoTemplate aNotificacaoTemplate) {

        return Try(() -> gateway.update(aNotificacaoTemplate))
                .toEither().bimap(Notification::create, UpdateNotificacaoTemplateOutput::from);
    }
}
