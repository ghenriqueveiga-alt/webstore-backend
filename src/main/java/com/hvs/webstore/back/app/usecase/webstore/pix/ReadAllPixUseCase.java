package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.ReadAllPixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.ReadAllPixOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllPixUseCase extends UseCase<ReadAllPixCommand, Either<Notification, ReadAllPixOutput>> {}
