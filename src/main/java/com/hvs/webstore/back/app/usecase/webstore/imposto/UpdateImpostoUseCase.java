package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.app.command.webstore.imposto.UpdateImpostoCommand;
import com.hvs.webstore.back.app.output.webstore.imposto.UpdateImpostoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateImpostoUseCase extends UseCase<UpdateImpostoCommand, Either<Notification, UpdateImpostoOutput>> {}
