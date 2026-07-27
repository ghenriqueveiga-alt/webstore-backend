package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.DeleteRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.DeleteRoleOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteRoleUseCase extends UseCase<DeleteRoleCommand, Either<Notification, DeleteRoleOutput>> {}
