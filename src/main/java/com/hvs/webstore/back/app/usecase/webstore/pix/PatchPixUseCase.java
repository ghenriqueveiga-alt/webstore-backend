package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.app.command.webstore.pix.PatchPixCommand;
import com.hvs.webstore.back.app.output.webstore.pix.PatchPixOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchPixUseCase extends UseCase<PatchPixCommand, Either<Notification, PatchPixOutput>> {}
