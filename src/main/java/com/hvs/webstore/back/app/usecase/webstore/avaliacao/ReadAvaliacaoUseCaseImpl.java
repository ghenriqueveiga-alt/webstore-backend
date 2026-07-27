package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.ReadAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.AvaliacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoId;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadAvaliacaoUseCaseImpl extends ReadAvaliacaoUseCase {

    private final AvaliacaoDomainGateway gateway;

    public ReadAvaliacaoUseCaseImpl(AvaliacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, AvaliacaoOutput> execute(ReadAvaliacaoCommand aAvaliacaoCommand) {

        Optional<Avaliacao> aAvaliacaoDB = aAvaliacaoCommand.aId() != null ?
                gateway.read(AvaliacaoId.from(aAvaliacaoCommand.aId())) : gateway.readByUuid(AvaliacaoUuid.from(aAvaliacaoCommand.aUuid()));

        if (aAvaliacaoDB.isPresent())
            return Try(aAvaliacaoDB::get).toEither().bimap(Notification::create, AvaliacaoOutput::from);

        var aAvaliacaoId = aAvaliacaoCommand.aId() != null ? String.valueOf(aAvaliacaoCommand.aId()) : aAvaliacaoCommand.aUuid();

        return Either.left(Notification.create(new Error("Avaliacao not found: " + aAvaliacaoId)));
    }
}
