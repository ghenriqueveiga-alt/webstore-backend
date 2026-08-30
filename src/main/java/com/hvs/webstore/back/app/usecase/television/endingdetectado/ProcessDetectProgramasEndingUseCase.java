package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectProgramasEndingCommand;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectProgramasEndingOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectProgramasEndingUseCase extends UseCase<ProcessDetectProgramasEndingCommand, Either<Notification, ProcessDetectProgramasEndingOutput>> {
}
