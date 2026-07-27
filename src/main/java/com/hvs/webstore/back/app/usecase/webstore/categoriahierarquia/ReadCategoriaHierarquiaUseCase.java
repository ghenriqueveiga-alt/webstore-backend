package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.ReadCategoriaHierarquiaCommand;
import com.hvs.webstore.back.app.output.webstore.categoriahierarquia.ReadCategoriaHierarquiaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCategoriaHierarquiaUseCase extends UseCase<ReadCategoriaHierarquiaCommand, Either<Notification, ReadCategoriaHierarquiaOutput>> {}
