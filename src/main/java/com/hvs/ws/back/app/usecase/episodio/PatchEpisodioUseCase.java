package com.hvs.ws.back.app.usecase.episodio;

import com.hvs.ws.back.app.command.episodio.PatchEpisodioCommand;
import com.hvs.ws.back.app.output.episodio.PatchEpisodioOutput;
import com.hvs.ws.back.app.usecase.UseCase;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

public abstract class PatchEpisodioUseCase extends UseCase<PatchEpisodioCommand, Either<Notification, PatchEpisodioOutput>> {
}