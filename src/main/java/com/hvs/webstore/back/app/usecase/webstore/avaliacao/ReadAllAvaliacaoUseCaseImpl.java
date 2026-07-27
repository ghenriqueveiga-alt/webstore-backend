package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.ReadAllAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.ReadAllAvaliacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllAvaliacaoUseCaseImpl extends ReadAllAvaliacaoUseCase {

    private final AvaliacaoDomainGateway gateway;

    public ReadAllAvaliacaoUseCaseImpl(AvaliacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllAvaliacaoOutput> execute(ReadAllAvaliacaoCommand aAvaliacaoCommand) {

        Pagination<Avaliacao> avaliacaoPagination = gateway.readAll(aAvaliacaoCommand.aSearchQuery());
        List<Avaliacao> lista = avaliacaoPagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aAvaliacaoCommand.aSearchQuery())).toEither().bimap(Notification::create, ReadAllAvaliacaoOutput::from);

        return Either.left(Notification.create(new Error("No Avaliacao was found.")));
    }
}
