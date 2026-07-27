package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.PatchMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.PatchMetaLojaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchMetaLojaUseCase extends UseCase<PatchMetaLojaCommand, Either<Notification, PatchMetaLojaOutput>> {}
