package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.UpdateNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.UpdateNotaFiscalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateNotaFiscalUseCase extends UseCase<UpdateNotaFiscalCommand, Either<Notification, UpdateNotaFiscalOutput>> {}
