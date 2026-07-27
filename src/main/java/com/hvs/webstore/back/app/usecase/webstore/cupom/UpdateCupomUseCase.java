package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.UpdateCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.UpdateCupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateCupomUseCase extends UseCase<UpdateCupomCommand, Either<Notification, UpdateCupomOutput>> {}
