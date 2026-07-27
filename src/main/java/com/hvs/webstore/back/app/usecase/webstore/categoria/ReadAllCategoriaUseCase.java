package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.ReadAllCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.ReadAllCategoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCategoriaUseCase extends UseCase<ReadAllCategoriaCommand, Either<Notification, ReadAllCategoriaOutput>> {}
