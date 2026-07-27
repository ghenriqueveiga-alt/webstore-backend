package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.ReadCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.CupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadCupomUseCase extends UseCase<ReadCupomCommand, Either<Notification, CupomOutput>> {}
