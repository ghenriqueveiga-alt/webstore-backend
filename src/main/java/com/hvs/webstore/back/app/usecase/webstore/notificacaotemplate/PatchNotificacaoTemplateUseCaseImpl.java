package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.PatchNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.PatchNotificacaoTemplateOutput;
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

public class PatchNotificacaoTemplateUseCaseImpl extends PatchNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public PatchNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchNotificacaoTemplateOutput> execute(PatchNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        Optional<NotificacaoTemplate> aNotificacaoTemplateDB = aNotificacaoTemplateCommand.aId() != null ?
                gateway.read(NotificacaoTemplateId.from(aNotificacaoTemplateCommand.aId())) : gateway.readByUuid(NotificacaoTemplateUuid.from(aNotificacaoTemplateCommand.aUuid()));

        if (aNotificacaoTemplateDB.isEmpty())
            return Left(Notification.create(new Error("NotificacaoTemplate not found: " + (aNotificacaoTemplateCommand.aId() != null ?
                    aNotificacaoTemplateCommand.aId() : aNotificacaoTemplateCommand.aUuid()))));

        var notification = Notification.create();
        var tipo = aNotificacaoTemplateCommand.aTipoCode() != null ? TipoNotificacaoTemplate.findByCode(aNotificacaoTemplateCommand.aTipoCode()) : null;
        var notificacaoTemplate = NotificacaoTemplate.patch(aNotificacaoTemplateCommand.aStatusCode(),
                                                            aNotificacaoTemplateCommand.aNome(), tipo,
                                                            aNotificacaoTemplateCommand.aAssunto(),
                                                            aNotificacaoTemplateCommand.aCorpo(),
                                                            aNotificacaoTemplateCommand.aVariaveis(),
                                                            aNotificacaoTemplateDB.get());
        notificacaoTemplate.validate(notification);

        return notification.hasError() ? Left(notification) : patch(notificacaoTemplate);
    }
    @Transactional
    private Either<Notification, PatchNotificacaoTemplateOutput> patch(NotificacaoTemplate aNotificacaoTemplate) {

        return Try(() -> gateway.patch(aNotificacaoTemplate))
                .toEither().bimap(Notification::create, PatchNotificacaoTemplateOutput::from);
    }
}
