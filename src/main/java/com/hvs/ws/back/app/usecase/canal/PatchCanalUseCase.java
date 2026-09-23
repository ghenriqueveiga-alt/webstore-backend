package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.PatchCanalCommand;
import com.hvs.ws.back.app.output.canal.PatchCanalOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCanalUseCase extends UseCase<PatchCanalCommand, Either<Notification, PatchCanalOutput>> {
}
