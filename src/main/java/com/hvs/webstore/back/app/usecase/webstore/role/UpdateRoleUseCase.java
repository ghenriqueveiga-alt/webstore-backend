package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.UpdateRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.UpdateRoleOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateRoleUseCase extends UseCase<UpdateRoleCommand, Either<Notification, UpdateRoleOutput>> {}
