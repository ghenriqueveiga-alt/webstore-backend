package com.hvs.ws.back.app.usecase.genero;

import com.hvs.ws.back.app.command.genero.PatchGeneroCommand;
import com.hvs.ws.back.app.output.genero.PatchGeneroOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchGeneroUseCase extends UseCase<PatchGeneroCommand, Either<Notification, PatchGeneroOutput>> {
}
