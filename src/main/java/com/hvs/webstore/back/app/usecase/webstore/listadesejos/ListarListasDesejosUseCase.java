package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.output.webstore.listadesejos.ReadAllListaDesejosOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ListarListasDesejosUseCase extends UseCase<Void, Either<Notification, ReadAllListaDesejosOutput>> {}
