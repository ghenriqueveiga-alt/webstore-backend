package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.ReadAllCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.ReadAllCupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllCupomUseCase extends UseCase<ReadAllCupomCommand, Either<Notification, ReadAllCupomOutput>> {}
