package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.ReadAllNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.ReadAllNotaFiscalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllNotaFiscalUseCase extends UseCase<ReadAllNotaFiscalCommand, Either<Notification, ReadAllNotaFiscalOutput>> {}
