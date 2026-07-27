package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.PatchNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.PatchNotaFiscalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchNotaFiscalUseCase extends UseCase<PatchNotaFiscalCommand, Either<Notification, PatchNotaFiscalOutput>> {}
