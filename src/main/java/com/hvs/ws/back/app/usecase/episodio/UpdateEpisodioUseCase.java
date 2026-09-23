package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.UpdateEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.UpdateEpisodioOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateEpisodioUseCase extends UseCase<UpdateEpisodioCommand, Either<Notification, UpdateEpisodioOutput>> {
}