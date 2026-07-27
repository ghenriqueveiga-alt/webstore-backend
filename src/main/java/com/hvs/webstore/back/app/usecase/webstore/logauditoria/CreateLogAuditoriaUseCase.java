package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.CreateLogAuditoriaCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.CreateLogAuditoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateLogAuditoriaUseCase extends UseCase<CreateLogAuditoriaCommand, Either<Notification, CreateLogAuditoriaOutput>> {}
