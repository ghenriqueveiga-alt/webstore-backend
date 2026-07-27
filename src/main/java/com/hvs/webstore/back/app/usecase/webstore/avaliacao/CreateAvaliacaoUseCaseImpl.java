package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.CreateAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.CreateAvaliacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateAvaliacaoUseCaseImpl extends CreateAvaliacaoUseCase {

    private final AvaliacaoDomainGateway gateway;

    public CreateAvaliacaoUseCaseImpl(AvaliacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateAvaliacaoOutput> execute(CreateAvaliacaoCommand aAvaliacaoCommand) {

        var notification = Notification.create();
        var avaliacao = Avaliacao.create(aAvaliacaoCommand.aProdutoId(),
                                         aAvaliacaoCommand.aUsuarioId(),
                                         aAvaliacaoCommand.nota(),
                                         aAvaliacaoCommand.titulo(),
                                         aAvaliacaoCommand.comentario());
        avaliacao.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(avaliacao);
    }

    @Transactional
    private Either<Notification, CreateAvaliacaoOutput> create(Avaliacao aAvaliacao) {

        return Try(() -> gateway.create(aAvaliacao))
                .toEither().bimap(Notification::create, CreateAvaliacaoOutput::from);
    }
}
