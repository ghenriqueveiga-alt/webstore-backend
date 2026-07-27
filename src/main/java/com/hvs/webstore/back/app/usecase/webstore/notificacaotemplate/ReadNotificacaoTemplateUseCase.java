package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.ReadNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.ReadNotificacaoTemplateOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadNotificacaoTemplateUseCase extends UseCase<ReadNotificacaoTemplateCommand, Either<Notification, ReadNotificacaoTemplateOutput>> {}
