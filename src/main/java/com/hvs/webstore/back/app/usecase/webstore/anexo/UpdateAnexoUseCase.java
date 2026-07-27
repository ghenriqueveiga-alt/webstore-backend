package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.UpdateAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.UpdateAnexoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateAnexoUseCase extends UseCase<UpdateAnexoCommand, Either<Notification, UpdateAnexoOutput>> {}
