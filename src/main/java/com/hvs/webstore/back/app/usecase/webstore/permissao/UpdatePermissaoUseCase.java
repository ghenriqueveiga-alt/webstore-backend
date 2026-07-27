package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.UpdatePermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.UpdatePermissaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdatePermissaoUseCase extends UseCase<UpdatePermissaoCommand, Either<Notification, UpdatePermissaoOutput>> {}
