package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.UpdateMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.UpdateMetaLojaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateMetaLojaUseCase extends UseCase<UpdateMetaLojaCommand, Either<Notification, UpdateMetaLojaOutput>> {}
