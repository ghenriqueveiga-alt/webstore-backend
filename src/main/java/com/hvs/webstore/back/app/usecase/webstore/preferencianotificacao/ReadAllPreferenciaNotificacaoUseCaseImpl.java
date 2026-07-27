package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.ReadAllPreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.ReadAllPreferenciaNotificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllPreferenciaNotificacaoUseCaseImpl extends ReadAllPreferenciaNotificacaoUseCase {

    private final PreferenciaNotificacaoDomainGateway gateway;

    public ReadAllPreferenciaNotificacaoUseCaseImpl(PreferenciaNotificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllPreferenciaNotificacaoOutput> execute(ReadAllPreferenciaNotificacaoCommand aPreferenciaNotificacaoCommand) {

        return Try(() -> gateway.readAll(aPreferenciaNotificacaoCommand.aSearchQuery()))
                .toEither().bimap(Notification::create, ReadAllPreferenciaNotificacaoOutput::from);
    }
}
