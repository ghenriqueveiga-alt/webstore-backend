package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.ReadAllPrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.ReadAllPrecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllPrecoUseCase extends UseCase<ReadAllPrecoCommand, Either<Notification, ReadAllPrecoOutput>> {}
