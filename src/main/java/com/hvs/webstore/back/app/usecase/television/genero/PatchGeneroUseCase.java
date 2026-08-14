package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.PatchGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.PatchGeneroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchGeneroUseCase extends UseCase<PatchGeneroCommand, Either<Notification, PatchGeneroOutput>> {
}
