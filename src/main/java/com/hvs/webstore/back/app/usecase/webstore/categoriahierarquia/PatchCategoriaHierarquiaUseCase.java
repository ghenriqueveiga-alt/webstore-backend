package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.PatchCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.PatchCategoriaHierarquiaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCategoriaHierarquiaUseCase extends UseCase<PatchCategoriaHierarquiaCommand, Either<Notification, PatchCategoriaHierarquiaOutput>> {}
