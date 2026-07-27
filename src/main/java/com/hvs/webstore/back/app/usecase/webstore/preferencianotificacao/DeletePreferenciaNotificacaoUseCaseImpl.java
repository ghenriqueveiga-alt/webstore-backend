package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.DeletePreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.DeletePreferenciaNotificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeletePreferenciaNotificacaoUseCaseImpl extends DeletePreferenciaNotificacaoUseCase {

    private final PreferenciaNotificacaoDomainGateway gateway;

    public DeletePreferenciaNotificacaoUseCaseImpl(PreferenciaNotificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeletePreferenciaNotificacaoOutput> execute(DeletePreferenciaNotificacaoCommand aPreferenciaNotificacaoCommand) {

        Optional<PreferenciaNotificacao> aPreferenciaNotificacaoDB = aPreferenciaNotificacaoCommand.aId() != null ?
                gateway.read(PreferenciaNotificacaoId.from(aPreferenciaNotificacaoCommand.aId())) : gateway.readByUuid(PreferenciaNotificacaoUuid.from(aPreferenciaNotificacaoCommand.aUuid()));

        if (aPreferenciaNotificacaoDB.isEmpty())
            return Either.left(Notification.create(new Error("PreferenciaNotificacao not found: " + (aPreferenciaNotificacaoCommand.aId() != null ?
                    aPreferenciaNotificacaoCommand.aId() : aPreferenciaNotificacaoCommand.aUuid()))));

        return delete(aPreferenciaNotificacaoDB.get());
    }

    @Transactional
    private Either<Notification, DeletePreferenciaNotificacaoOutput> delete(PreferenciaNotificacao aPreferenciaNotificacao) {

        return Try(() -> {
            gateway.delete(aPreferenciaNotificacao);
            return DeletePreferenciaNotificacaoOutput.from(aPreferenciaNotificacao);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
