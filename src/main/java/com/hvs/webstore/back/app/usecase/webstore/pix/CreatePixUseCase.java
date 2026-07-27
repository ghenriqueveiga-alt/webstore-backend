package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.CreatePixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.CreatePixOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreatePixUseCase extends UseCase<CreatePixCommand, Either<Notification, CreatePixOutput>> {}
