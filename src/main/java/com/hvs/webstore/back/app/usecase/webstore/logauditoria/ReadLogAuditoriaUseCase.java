package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadLogAuditoriaCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadLogAuditoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadLogAuditoriaUseCase extends UseCase<ReadLogAuditoriaCommand, Either<Notification, ReadLogAuditoriaOutput>> {}
