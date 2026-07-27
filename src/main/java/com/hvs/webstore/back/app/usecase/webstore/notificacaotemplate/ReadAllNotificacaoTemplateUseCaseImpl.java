package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.ReadAllNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.ReadAllNotificacaoTemplateOutput;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllNotificacaoTemplateUseCaseImpl extends ReadAllNotificacaoTemplateUseCase {

    private final NotificacaoTemplateDomainGateway gateway;

    public ReadAllNotificacaoTemplateUseCaseImpl(NotificacaoTemplateDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllNotificacaoTemplateOutput> execute(ReadAllNotificacaoTemplateCommand aNotificacaoTemplateCommand) {

        Pagination<NotificacaoTemplate> pagination = gateway.readAll(aNotificacaoTemplateCommand.aSearchQuery());
        List<NotificacaoTemplate> lista = pagination.aContent().stream().filter(c -> c.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aNotificacaoTemplateCommand.aSearchQuery()))
                    .toEither().bimap(Notification::create, ReadAllNotificacaoTemplateOutput::from);

        return Either.left(Notification.create(new Error("No NotificacaoTemplate was found.")));
    }
}
