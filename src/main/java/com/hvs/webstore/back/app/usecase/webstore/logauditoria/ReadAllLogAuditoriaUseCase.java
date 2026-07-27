package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadAllLogAuditoriaCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadAllLogAuditoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllLogAuditoriaUseCase extends UseCase<ReadAllLogAuditoriaCommand, Either<Notification, ReadAllLogAuditoriaOutput>> {}
