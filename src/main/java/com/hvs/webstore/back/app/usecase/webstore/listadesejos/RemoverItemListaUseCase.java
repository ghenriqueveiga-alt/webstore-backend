package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.app.command.webstore.listadesejos.RemoverItemListaCommand;
import com.hvs.webstore.back.app.output.webstore.listadesejos.RemoverItemListaOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class RemoverItemListaUseCase extends UseCase<RemoverItemListaCommand, Either<Notification, RemoverItemListaOutput>> {}
