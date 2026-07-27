package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.CreatePermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.CreatePermissaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreatePermissaoUseCase extends UseCase<CreatePermissaoCommand, Either<Notification, CreatePermissaoOutput>> {}
