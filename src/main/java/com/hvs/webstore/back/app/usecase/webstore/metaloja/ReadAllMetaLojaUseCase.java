package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.ReadAllMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.ReadAllMetaLojaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllMetaLojaUseCase extends UseCase<ReadAllMetaLojaCommand, Either<Notification, ReadAllMetaLojaOutput>> {}
