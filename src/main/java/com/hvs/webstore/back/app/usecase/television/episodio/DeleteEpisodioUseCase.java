package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.DeleteEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.DeleteEpisodioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class DeleteEpisodioUseCase extends UseCase<DeleteEpisodioCommand, Either<Notification, DeleteEpisodioOutput>> {
}