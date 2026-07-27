package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.CreateEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.CreateEpisodioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateEpisodioUseCase extends UseCase<CreateEpisodioCommand, Either<Notification, CreateEpisodioOutput>> {
}