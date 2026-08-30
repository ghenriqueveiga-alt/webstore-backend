package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectadoIntroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectIntroUseCase extends UseCase<ProcessDetectIntroCommand, Either<Notification, ProcessDetectadoIntroOutput>> {
}