package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectEndingCommand;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectadoEndingOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectEndingUseCase extends UseCase<ProcessDetectEndingCommand, Either<Notification, ProcessDetectadoEndingOutput>> {
}
