package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.ReadPrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.ReadPrecoPromocionalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadPrecoPromocionalUseCase extends UseCase<ReadPrecoPromocionalCommand, Either<Notification, ReadPrecoPromocionalOutput>> {}
