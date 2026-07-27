package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.app.command.webstore.logauditoria.ReadLogAuditoriaByEntidadeCommand;
import com.hvs.webstore.back.app.output.webstore.logauditoria.ReadLogAuditoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.util.List;

public abstract class ReadLogAuditoriaByEntidadeUseCase extends UseCase<ReadLogAuditoriaByEntidadeCommand, Either<Notification, List<ReadLogAuditoriaOutput>>> {}
