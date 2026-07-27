package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.app.command.webstore.categoria.PatchCategoriaCommand;
import com.hvs.webstore.back.app.output.webstore.categoria.PatchCategoriaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCategoriaUseCase extends UseCase<PatchCategoriaCommand, Either<Notification, PatchCategoriaOutput>> {}
