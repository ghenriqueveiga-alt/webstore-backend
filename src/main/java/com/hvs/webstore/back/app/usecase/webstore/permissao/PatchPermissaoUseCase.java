package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.app.command.webstore.permissao.PatchPermissaoCommand;
import com.hvs.webstore.back.app.output.webstore.permissao.PatchPermissaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchPermissaoUseCase extends UseCase<PatchPermissaoCommand, Either<Notification, PatchPermissaoOutput>> {}
