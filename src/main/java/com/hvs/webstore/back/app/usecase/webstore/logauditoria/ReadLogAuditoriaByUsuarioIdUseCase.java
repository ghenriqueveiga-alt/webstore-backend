package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadLogAuditoriaByUsuarioIdCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadLogAuditoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.util.List;

public abstract class ReadLogAuditoriaByUsuarioIdUseCase extends UseCase<ReadLogAuditoriaByUsuarioIdCommand, Either<Notification, List<ReadLogAuditoriaOutput>>> {}
