package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.DeletePermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.DeletePermissaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeletePermissaoUseCase extends UseCase<DeletePermissaoCommand, Either<Notification, DeletePermissaoOutput>> {}
