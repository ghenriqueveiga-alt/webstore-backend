package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.DeleteMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.DeleteMetaLojaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteMetaLojaUseCase extends UseCase<DeleteMetaLojaCommand, Either<Notification, DeleteMetaLojaOutput>> {}
