package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.ReadImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.ReadImpostoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadImpostoUseCase extends UseCase<ReadImpostoCommand, Either<Notification, ReadImpostoOutput>> {}
