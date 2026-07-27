package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.CreateVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.CreateVideoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateVideoUseCase extends UseCase<CreateVideoCommand, Either<Notification, CreateVideoOutput>> {}
