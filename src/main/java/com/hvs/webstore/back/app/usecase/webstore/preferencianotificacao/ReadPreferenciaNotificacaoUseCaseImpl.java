package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.ReadPreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.ReadPreferenciaNotificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadPreferenciaNotificacaoUseCaseImpl extends ReadPreferenciaNotificacaoUseCase {

    private final PreferenciaNotificacaoDomainGateway gateway;

    public ReadPreferenciaNotificacaoUseCaseImpl(PreferenciaNotificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadPreferenciaNotificacaoOutput> execute(ReadPreferenciaNotificacaoCommand aPreferenciaNotificacaoCommand) {

        Optional<PreferenciaNotificacao> aPreferenciaNotificacaoDB = aPreferenciaNotificacaoCommand.aId() != null ?
                gateway.read(PreferenciaNotificacaoId.from(aPreferenciaNotificacaoCommand.aId())) : gateway.readByUuid(PreferenciaNotificacaoUuid.from(aPreferenciaNotificacaoCommand.aUuid()));

        if (aPreferenciaNotificacaoDB.isPresent())
            return Try(aPreferenciaNotificacaoDB::get).toEither().bimap(Notification::create, ReadPreferenciaNotificacaoOutput::from);

        var aPreferenciaNotificacaoId = aPreferenciaNotificacaoCommand.aId() != null ? String.valueOf(aPreferenciaNotificacaoCommand.aId()) : aPreferenciaNotificacaoCommand.aUuid();

        return Either.left(Notification.create(new Error("PreferenciaNotificacao not found: " + aPreferenciaNotificacaoId)));
    }
}
