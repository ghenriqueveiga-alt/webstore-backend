package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.DeleteNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.DeleteNotaFiscalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteNotaFiscalUseCase extends UseCase<DeleteNotaFiscalCommand, Either<Notification, DeleteNotaFiscalOutput>> {}
