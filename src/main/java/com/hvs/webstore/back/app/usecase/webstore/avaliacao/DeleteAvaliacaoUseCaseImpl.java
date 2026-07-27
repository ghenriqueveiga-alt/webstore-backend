package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.DeleteAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.DeleteAvaliacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoId;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteAvaliacaoUseCaseImpl extends DeleteAvaliacaoUseCase {

    private final AvaliacaoDomainGateway gateway;

    public DeleteAvaliacaoUseCaseImpl(AvaliacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteAvaliacaoOutput> execute(DeleteAvaliacaoCommand aAvaliacaoCommand) {

        Optional<Avaliacao> aAvaliacaoDB = aAvaliacaoCommand.aId() != null ?
                gateway.read(AvaliacaoId.from(aAvaliacaoCommand.aId())) : gateway.readByUuid(AvaliacaoUuid.from(aAvaliacaoCommand.aUuid()));

        if (aAvaliacaoDB.isEmpty())
            return Either.left(Notification.create(new Error("Avaliacao not found: " + (aAvaliacaoCommand.aId() != null ?
                    aAvaliacaoCommand.aId() : aAvaliacaoCommand.aUuid()))));

        return delete(aAvaliacaoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteAvaliacaoOutput> delete(Avaliacao aAvaliacao) {

        return Try(() -> {
            gateway.delete(aAvaliacao);
            return DeleteAvaliacaoOutput.from(aAvaliacao);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
