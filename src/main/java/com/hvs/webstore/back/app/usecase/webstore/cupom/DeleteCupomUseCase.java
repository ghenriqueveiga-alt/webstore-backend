package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.DeleteCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.DeleteCupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteCupomUseCase extends UseCase<DeleteCupomCommand, Either<Notification, DeleteCupomOutput>> {}
