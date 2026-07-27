package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.UpdateEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.UpdateEpisodioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class UpdateEpisodioUseCase extends UseCase<UpdateEpisodioCommand, Either<Notification, UpdateEpisodioOutput>> {
}