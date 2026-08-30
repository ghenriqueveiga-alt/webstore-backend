package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectProgramasIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectProgramasIntroOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ProcessDetectProgramasIntroUseCase extends UseCase<ProcessDetectProgramasIntroCommand, Either<Notification, ProcessDetectProgramasIntroOutput>> {
}