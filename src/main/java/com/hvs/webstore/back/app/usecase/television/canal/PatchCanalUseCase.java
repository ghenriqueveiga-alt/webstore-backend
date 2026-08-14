package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.PatchCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.PatchCanalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCanalUseCase extends UseCase<PatchCanalCommand, Either<Notification, PatchCanalOutput>> {
}
