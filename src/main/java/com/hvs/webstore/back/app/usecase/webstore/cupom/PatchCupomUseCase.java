package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.app.command.webstore.cupom.PatchCupomCommand;
import com.hvs.webstore.back.app.output.webstore.cupom.PatchCupomOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchCupomUseCase extends UseCase<PatchCupomCommand, Either<Notification, PatchCupomOutput>> {}
