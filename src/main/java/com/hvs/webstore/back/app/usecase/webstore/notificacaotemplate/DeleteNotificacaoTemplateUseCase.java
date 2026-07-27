package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.DeleteNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.DeleteNotificacaoTemplateOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteNotificacaoTemplateUseCase extends UseCase<DeleteNotificacaoTemplateCommand, Either<Notification, DeleteNotificacaoTemplateOutput>> {}
