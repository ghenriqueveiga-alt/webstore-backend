package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.ReadEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioCortesDetectadosOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class ReadEpisodioCortesDetectadosUseCase extends UseCase<ReadEpisodioCommand, Either<Notification, ReadEpisodioCortesDetectadosOutput>> {
}