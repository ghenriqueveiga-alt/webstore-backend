package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.ReadAllImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.ReadAllImpostoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllImpostoUseCase extends UseCase<ReadAllImpostoCommand, Either<Notification, ReadAllImpostoOutput>> {}
