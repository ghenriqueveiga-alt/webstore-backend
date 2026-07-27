package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.PatchPrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.PatchPrecoPromocionalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchPrecoPromocionalUseCase extends UseCase<PatchPrecoPromocionalCommand, Either<Notification, PatchPrecoPromocionalOutput>> {}
