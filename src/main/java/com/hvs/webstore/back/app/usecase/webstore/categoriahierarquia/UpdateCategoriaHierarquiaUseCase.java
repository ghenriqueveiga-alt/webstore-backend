package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.UpdateCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.UpdateCategoriaHierarquiaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoriaHierarquiaUseCase extends UseCase<UpdateCategoriaHierarquiaCommand, Either<Notification, UpdateCategoriaHierarquiaOutput>> {}
