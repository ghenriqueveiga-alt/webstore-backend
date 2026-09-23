package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.ReadEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.ReadEpisodioOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadEpisodioUseCase extends UseCase<ReadEpisodioCommand, Either<Notification, ReadEpisodioOutput>> {
}