package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.ReadAllNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.ReadAllNotificacaoTemplateOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllNotificacaoTemplateUseCase extends UseCase<ReadAllNotificacaoTemplateCommand, Either<Notification, ReadAllNotificacaoTemplateOutput>> {}
