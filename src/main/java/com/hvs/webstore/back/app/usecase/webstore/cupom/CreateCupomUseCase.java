package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.CreateCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.CreateCupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateCupomUseCase extends UseCase<CreateCupomCommand, Either<Notification, CreateCupomOutput>> {}
