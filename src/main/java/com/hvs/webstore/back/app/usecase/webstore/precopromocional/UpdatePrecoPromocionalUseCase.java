package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.UpdatePrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.UpdatePrecoPromocionalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdatePrecoPromocionalUseCase extends UseCase<UpdatePrecoPromocionalCommand, Either<Notification, UpdatePrecoPromocionalOutput>> {}
