package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.PatchRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.PatchRoleOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchRoleUseCase extends UseCase<PatchRoleCommand, Either<Notification, PatchRoleOutput>> {}
