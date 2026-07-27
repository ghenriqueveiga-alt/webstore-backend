package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.app.command.webstore.preco.ReadPrecoCommand;
import com.hvs.webstore.back.app.output.webstore.preco.ReadPrecoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPrecoUseCase extends UseCase<ReadPrecoCommand, Either<Notification, ReadPrecoOutput>> {}
