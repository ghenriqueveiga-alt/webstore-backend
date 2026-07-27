package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.ReadRootsCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.ReadAllCategoriaHierarquiaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadRootsUseCase extends UseCase<ReadRootsCommand, Either<Notification, ReadAllCategoriaHierarquiaOutput>> {}
