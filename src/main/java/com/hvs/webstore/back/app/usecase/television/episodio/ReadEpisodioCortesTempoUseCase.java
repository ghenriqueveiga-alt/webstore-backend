package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.ReadEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioCortesTempoOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadEpisodioCortesTempoUseCase extends UseCase<ReadEpisodioCommand, Either<Notification, ReadEpisodioCortesTempoOutput>> {
}