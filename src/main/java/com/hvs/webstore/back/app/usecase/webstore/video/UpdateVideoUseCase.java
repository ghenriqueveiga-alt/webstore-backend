package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.UpdateVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.UpdateVideoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateVideoUseCase extends UseCase<UpdateVideoCommand, Either<Notification, UpdateVideoOutput>> {}
