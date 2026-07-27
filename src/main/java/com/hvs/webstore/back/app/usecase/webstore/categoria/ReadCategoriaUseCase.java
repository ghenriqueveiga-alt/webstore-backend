package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.ReadCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.ReadCategoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCategoriaUseCase extends UseCase<ReadCategoriaCommand, Either<Notification, ReadCategoriaOutput>> {}
