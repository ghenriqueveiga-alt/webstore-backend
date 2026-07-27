package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.DeletePixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.DeletePixOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeletePixUseCase extends UseCase<DeletePixCommand, Either<Notification, DeletePixOutput>> {}
