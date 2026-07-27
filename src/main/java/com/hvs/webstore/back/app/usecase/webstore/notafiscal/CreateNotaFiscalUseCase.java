package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.CreateNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.CreateNotaFiscalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateNotaFiscalUseCase extends UseCase<CreateNotaFiscalCommand, Either<Notification, CreateNotaFiscalOutput>> {}
