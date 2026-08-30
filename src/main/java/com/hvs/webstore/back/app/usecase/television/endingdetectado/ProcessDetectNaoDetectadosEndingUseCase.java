package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectNaoDetectadosEndingCommand;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectAllEndingOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectNaoDetectadosEndingUseCase
        extends UseCase<ProcessDetectNaoDetectadosEndingCommand, Either<Notification, ProcessDetectAllEndingOutput>> {
}
