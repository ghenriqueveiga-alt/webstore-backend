package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.PatchNotificacaoTemplateCommand;
import com.hvs.webstore.back.app.output.webstore.notificacaotemplate.PatchNotificacaoTemplateOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchNotificacaoTemplateUseCase extends UseCase<PatchNotificacaoTemplateCommand, Either<Notification, PatchNotificacaoTemplateOutput>> {}
