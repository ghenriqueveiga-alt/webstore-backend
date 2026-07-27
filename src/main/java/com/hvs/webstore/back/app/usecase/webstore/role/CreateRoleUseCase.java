package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.app.command.webstore.role.CreateRoleCommand;
import com.hvs.webstore.back.app.output.webstore.role.CreateRoleOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateRoleUseCase extends UseCase<CreateRoleCommand, Either<Notification, CreateRoleOutput>> {}
