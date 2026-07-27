package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.CreateCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.CreateCategoriaHierarquiaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoriaHierarquiaUseCase extends UseCase<CreateCategoriaHierarquiaCommand, Either<Notification, CreateCategoriaHierarquiaOutput>> {}
