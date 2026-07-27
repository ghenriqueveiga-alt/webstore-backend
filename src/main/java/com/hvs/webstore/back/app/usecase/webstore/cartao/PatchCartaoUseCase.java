package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.app.command.webstore.cartao.PatchCartaoCommand;
import com.hvs.webstore.back.app.output.webstore.cartao.PatchCartaoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCartaoUseCase extends UseCase<PatchCartaoCommand, Either<Notification, PatchCartaoOutput>> {}
