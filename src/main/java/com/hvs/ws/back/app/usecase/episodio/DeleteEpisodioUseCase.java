package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.DeleteEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.DeleteEpisodioOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteEpisodioUseCase extends UseCase<DeleteEpisodioCommand, Either<Notification, DeleteEpisodioOutput>> {
}