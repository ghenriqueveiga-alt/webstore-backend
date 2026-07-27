package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.output.webstore.listadesejos.ListaDesejosOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class VisualizarListaDesejosUseCase extends UseCase<String, Either<Notification, ListaDesejosOutput>> {}
