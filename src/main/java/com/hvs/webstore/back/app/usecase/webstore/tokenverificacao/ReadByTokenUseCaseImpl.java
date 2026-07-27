package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.ReadByTokenCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.ReadTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadByTokenUseCaseImpl extends ReadByTokenUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public ReadByTokenUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadTokenVerificacaoOutput> execute(ReadByTokenCommand aTokenVerificacaoCommand) {

        return Try(() -> gateway.readByToken(aTokenVerificacaoCommand.aToken()).orElseThrow(() -> new Error("TokenVerificacao not found: " + aTokenVerificacaoCommand.aToken())))
                .toEither().bimap(Notification::create, ReadTokenVerificacaoOutput::from);
    }
}
