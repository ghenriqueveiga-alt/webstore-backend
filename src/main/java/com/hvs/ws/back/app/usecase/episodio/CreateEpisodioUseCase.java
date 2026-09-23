package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.CreateEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.CreateEpisodioOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class CreateEpisodioUseCase extends UseCase<CreateEpisodioCommand, Either<Notification, CreateEpisodioOutput>> {
}