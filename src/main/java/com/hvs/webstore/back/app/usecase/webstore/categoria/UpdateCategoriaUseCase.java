package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.UpdateCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.UpdateCategoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoriaUseCase extends UseCase<UpdateCategoriaCommand, Either<Notification, UpdateCategoriaOutput>> {}
