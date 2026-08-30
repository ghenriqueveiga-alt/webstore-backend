package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectAllIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectAllIntroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectAllIntroUseCase extends UseCase<ProcessDetectAllIntroCommand, Either<Notification, ProcessDetectAllIntroOutput>> {
}