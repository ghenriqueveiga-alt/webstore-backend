package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.CreateMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.CreateMetaLojaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateMetaLojaUseCase extends UseCase<CreateMetaLojaCommand, Either<Notification, CreateMetaLojaOutput>> {}
