package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.PatchEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.PatchEpisodioOutput;
import com.hvs.webstore.back.app.usecase.UseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchEpisodioUseCase extends UseCase<PatchEpisodioCommand, Either<Notification, PatchEpisodioOutput>> {
}