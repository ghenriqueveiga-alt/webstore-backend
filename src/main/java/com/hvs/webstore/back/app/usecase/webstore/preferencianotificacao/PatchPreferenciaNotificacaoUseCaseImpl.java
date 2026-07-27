package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.PatchPreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.PatchPreferenciaNotificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchPreferenciaNotificacaoUseCaseImpl extends PatchPreferenciaNotificacaoUseCase {

    private final PreferenciaNotificacaoDomainGateway gateway;

    public PatchPreferenciaNotificacaoUseCaseImpl(PreferenciaNotificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchPreferenciaNotificacaoOutput> execute(PatchPreferenciaNotificacaoCommand aPreferenciaNotificacaoCommand) {

        Optional<PreferenciaNotificacao> aPreferenciaNotificacaoDB = aPreferenciaNotificacaoCommand.aId() != null ?
                gateway.read(PreferenciaNotificacaoId.from(aPreferenciaNotificacaoCommand.aId())) : gateway.readByUuid(PreferenciaNotificacaoUuid.from(aPreferenciaNotificacaoCommand.aUuid()));

        if (aPreferenciaNotificacaoDB.isEmpty())
            return Left(Notification.create(new Error("PreferenciaNotificacao not found: " + (aPreferenciaNotificacaoCommand.aId() != null ?
                    aPreferenciaNotificacaoCommand.aId() : aPreferenciaNotificacaoCommand.aUuid()))));

        var notification = Notification.create();
        var preferenciaNotificacao = PreferenciaNotificacao.patch(aPreferenciaNotificacaoCommand.aStatusCode(),
                                                                   aPreferenciaNotificacaoCommand.aUsuarioId(),
                                                                   aPreferenciaNotificacaoCommand.aTipoCode(),
                                                                   aPreferenciaNotificacaoCommand.aAtivo(),
                                                                   aPreferenciaNotificacaoDB.get());
        preferenciaNotificacao.validate(notification);

        return notification.hasError() ? Left(notification) : patch(preferenciaNotificacao);
    }
    @Transactional
    private Either<Notification, PatchPreferenciaNotificacaoOutput> patch(PreferenciaNotificacao aPreferenciaNotificacao) {

        return Try(() -> gateway.patch(aPreferenciaNotificacao))
                .toEither().bimap(Notification::create, PatchPreferenciaNotificacaoOutput::from);
    }
}
