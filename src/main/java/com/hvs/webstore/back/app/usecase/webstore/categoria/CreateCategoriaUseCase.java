package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.CreateCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.CreateCategoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoriaUseCase extends UseCase<CreateCategoriaCommand, Either<Notification, CreateCategoriaOutput>> {}
