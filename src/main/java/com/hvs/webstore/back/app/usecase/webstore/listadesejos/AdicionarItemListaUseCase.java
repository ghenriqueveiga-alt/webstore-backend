package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.command.webstore.listadesejos.AdicionarItemListaCommand;
import com.hvs.webstore.back.app.output.webstore.listadesejos.AdicionarItemListaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class AdicionarItemListaUseCase extends UseCase<AdicionarItemListaCommand, Either<Notification, AdicionarItemListaOutput>> {}
