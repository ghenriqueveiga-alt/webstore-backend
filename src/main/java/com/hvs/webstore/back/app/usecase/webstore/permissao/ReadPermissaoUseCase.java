package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.ReadPermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.ReadPermissaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPermissaoUseCase extends UseCase<ReadPermissaoCommand, Either<Notification, ReadPermissaoOutput>> {}
