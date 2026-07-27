package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.CreateNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.CreateNotificacaoTemplateOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateNotificacaoTemplateUseCase extends UseCase<CreateNotificacaoTemplateCommand, Either<Notification, CreateNotificacaoTemplateOutput>> {}
