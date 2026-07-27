package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.command.webstore.listadesejos.CriarListaDesejosCommand;
import com.hvs.webstore.back.app.output.webstore.listadesejos.CriarListaDesejosOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CriarListaDesejosUseCase extends UseCase<CriarListaDesejosCommand, Either<Notification, CriarListaDesejosOutput>> {}
