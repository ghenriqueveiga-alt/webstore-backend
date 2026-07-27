package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.CreateTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.CreateTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateTokenVerificacaoUseCaseImpl extends CreateTokenVerificacaoUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public CreateTokenVerificacaoUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateTokenVerificacaoOutput> execute(CreateTokenVerificacaoCommand aTokenVerificacaoCommand) {

        var notification = Notification.create();
        var token = TokenVerificacao.create(aTokenVerificacaoCommand.aUsuarioId(),
                                            aTokenVerificacaoCommand.aToken(),
                                            aTokenVerificacaoCommand.aTipoCode(),
                                            aTokenVerificacaoCommand.aExpiradoEm());
        token.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(token);
    }

    @Transactional
    private Either<Notification, CreateTokenVerificacaoOutput> create(TokenVerificacao aTokenVerificacao) {

        return Try(() -> gateway.create(aTokenVerificacao))
                .toEither().bimap(Notification::create, CreateTokenVerificacaoOutput::from);
    }
}
