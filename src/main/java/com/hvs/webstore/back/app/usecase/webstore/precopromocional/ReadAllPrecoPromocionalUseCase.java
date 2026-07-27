package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.ReadAllPrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.ReadAllPrecoPromocionalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllPrecoPromocionalUseCase extends UseCase<ReadAllPrecoPromocionalCommand, Either<Notification, ReadAllPrecoPromocionalOutput>> {}
