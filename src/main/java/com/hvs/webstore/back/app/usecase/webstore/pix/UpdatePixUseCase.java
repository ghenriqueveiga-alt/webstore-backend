package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.UpdatePixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.UpdatePixOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdatePixUseCase extends UseCase<UpdatePixCommand, Either<Notification, UpdatePixOutput>> {}
