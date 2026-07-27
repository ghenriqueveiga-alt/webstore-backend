package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.ReadPixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.ReadPixOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPixUseCase extends UseCase<ReadPixCommand, Either<Notification, ReadPixOutput>> {}
