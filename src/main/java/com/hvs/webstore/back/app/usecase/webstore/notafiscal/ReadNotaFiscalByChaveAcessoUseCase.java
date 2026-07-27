package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.ReadNotaFiscalByChaveAcessoCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.ReadNotaFiscalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadNotaFiscalByChaveAcessoUseCase extends UseCase<ReadNotaFiscalByChaveAcessoCommand, Either<Notification, ReadNotaFiscalOutput>> {}
