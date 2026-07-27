package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.ReadAllPermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.ReadAllPermissaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllPermissaoUseCase extends UseCase<ReadAllPermissaoCommand, Either<Notification, ReadAllPermissaoOutput>> {}
