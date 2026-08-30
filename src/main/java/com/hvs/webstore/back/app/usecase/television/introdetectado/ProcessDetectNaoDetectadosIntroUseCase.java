package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectNaoDetectadosIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectAllIntroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectNaoDetectadosIntroUseCase
        extends UseCase<ProcessDetectNaoDetectadosIntroCommand, Either<Notification, ProcessDetectAllIntroOutput>> {
}