package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.ValidarCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.CupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ValidarCupomUseCase extends UseCase<ValidarCupomCommand, Either<Notification, CupomOutput>> {}
