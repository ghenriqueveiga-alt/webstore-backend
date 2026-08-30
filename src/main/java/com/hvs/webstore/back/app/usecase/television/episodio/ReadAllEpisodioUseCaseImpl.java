package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.ReadAllEpisodioCommand;
import com.hvs.webstore.back.app.output.television.episodio.ReadAllEpisodioOutput;
import com.hvs.webstore.back.app.usecase.television.episodio.ReadAllEpisodioUseCase;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllEpisodioUseCaseImpl extends ReadAllEpisodioUseCase {

    private final EpisodioDomainGateway gateway;

    public ReadAllEpisodioUseCaseImpl(
            EpisodioDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllEpisodioOutput> execute(ReadAllEpisodioCommand aIn) {

        Pagination<Episodio> episodioPagination = this.gateway.readAll(aIn.aEpisodioSearchQuery());
        List<Episodio> lista = episodioPagination.aContent()
                .stream().filter(corte -> corte.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Either.right(ReadAllEpisodioOutput.from(Pagination.from(
                    episodioPagination.aPageNumber(),
                    episodioPagination.aTotalElements(),
                    episodioPagination.aTotalPages(),
                    lista)));
        } else {

            return Either.left(Notification
                    .create(new Error("No Episode was found.")));
        }
    }
}
