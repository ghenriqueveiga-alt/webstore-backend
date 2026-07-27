package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.ReadAllVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.ReadAllVideoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllVideoUseCase extends UseCase<ReadAllVideoCommand, Either<Notification, ReadAllVideoOutput>> {}
