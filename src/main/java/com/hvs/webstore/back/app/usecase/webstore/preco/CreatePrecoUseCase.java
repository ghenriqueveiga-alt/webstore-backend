package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.CreatePrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.CreatePrecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreatePrecoUseCase extends UseCase<CreatePrecoCommand, Either<Notification, CreatePrecoOutput>> {}
