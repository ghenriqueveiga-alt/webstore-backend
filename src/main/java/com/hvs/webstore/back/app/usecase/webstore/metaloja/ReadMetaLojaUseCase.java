package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.ReadMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.ReadMetaLojaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadMetaLojaUseCase extends UseCase<ReadMetaLojaCommand, Either<Notification, ReadMetaLojaOutput>> {}
