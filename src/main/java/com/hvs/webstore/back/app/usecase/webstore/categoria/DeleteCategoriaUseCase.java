package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.DeleteCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.DeleteCategoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCategoriaUseCase extends UseCase<DeleteCategoriaCommand, Either<Notification, DeleteCategoriaOutput>> {}
