package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.ReadAllEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.ReadAllEpisodioOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadAllEpisodioUseCase extends UseCase<ReadAllEpisodioCommand, Either<Notification, ReadAllEpisodioOutput>> {
}