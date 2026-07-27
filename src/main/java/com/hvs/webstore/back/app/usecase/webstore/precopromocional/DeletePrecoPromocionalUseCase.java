package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.app.command.webstore.precopromocional.DeletePrecoPromocionalCommand;
import com.hvs.webstore.back.app.output.webstore.precopromocional.DeletePrecoPromocionalOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeletePrecoPromocionalUseCase extends UseCase<DeletePrecoPromocionalCommand, Either<Notification, DeletePrecoPromocionalOutput>> {}
