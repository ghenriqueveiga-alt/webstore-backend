package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.CreateImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.CreateImpostoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateImpostoUseCase extends UseCase<CreateImpostoCommand, Either<Notification, CreateImpostoOutput>> {}
