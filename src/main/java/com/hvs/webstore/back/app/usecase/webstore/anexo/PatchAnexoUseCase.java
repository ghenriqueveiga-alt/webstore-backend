package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.PatchAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.PatchAnexoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchAnexoUseCase extends UseCase<PatchAnexoCommand, Either<Notification, PatchAnexoOutput>> {}
