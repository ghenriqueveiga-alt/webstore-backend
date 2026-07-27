package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.DeleteVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.DeleteVideoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteVideoUseCase extends UseCase<DeleteVideoCommand, Either<Notification, DeleteVideoOutput>> {}
