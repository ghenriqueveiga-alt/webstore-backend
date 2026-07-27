package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.ReadAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.ReadAnexoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAnexoUseCase extends UseCase<ReadAnexoCommand, Either<Notification, ReadAnexoOutput>> {}
