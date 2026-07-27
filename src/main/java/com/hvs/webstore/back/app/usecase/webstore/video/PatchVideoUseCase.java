package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.PatchVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.PatchVideoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchVideoUseCase extends UseCase<PatchVideoCommand, Either<Notification, PatchVideoOutput>> {}
