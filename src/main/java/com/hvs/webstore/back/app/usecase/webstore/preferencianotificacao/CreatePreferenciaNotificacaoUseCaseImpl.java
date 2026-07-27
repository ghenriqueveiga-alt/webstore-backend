package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.CreatePreferenciaNotificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.preferencianotificacao.CreatePreferenciaNotificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreatePreferenciaNotificacaoUseCaseImpl extends CreatePreferenciaNotificacaoUseCase {

    private final PreferenciaNotificacaoDomainGateway gateway;

    public CreatePreferenciaNotificacaoUseCaseImpl(PreferenciaNotificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreatePreferenciaNotificacaoOutput> execute(CreatePreferenciaNotificacaoCommand aPreferenciaNotificacaoCommand) {

        var notification = Notification.create();
        var preferenciaNotificacao = PreferenciaNotificacao.create(aPreferenciaNotificacaoCommand.aUsuarioId(),
                                                                   aPreferenciaNotificacaoCommand.aTipoCode(),
                                                                   aPreferenciaNotificacaoCommand.aAtivo());
        preferenciaNotificacao.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(preferenciaNotificacao);
    }

    @Transactional
    private Either<Notification, CreatePreferenciaNotificacaoOutput> create(PreferenciaNotificacao aPreferenciaNotificacao) {

        return Try(() -> gateway.create(aPreferenciaNotificacao))
                .toEither().bimap(Notification::create, CreatePreferenciaNotificacaoOutput::from);
    }
}
