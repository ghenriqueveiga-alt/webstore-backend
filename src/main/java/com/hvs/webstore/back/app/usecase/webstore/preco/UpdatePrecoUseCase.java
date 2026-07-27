package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.UpdatePrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.UpdatePrecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdatePrecoUseCase extends UseCase<UpdatePrecoCommand, Either<Notification, UpdatePrecoOutput>> {}
