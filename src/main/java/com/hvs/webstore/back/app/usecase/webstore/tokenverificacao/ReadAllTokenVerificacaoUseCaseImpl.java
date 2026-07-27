package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.ReadAllTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.ReadAllTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllTokenVerificacaoUseCaseImpl extends ReadAllTokenVerificacaoUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public ReadAllTokenVerificacaoUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllTokenVerificacaoOutput> execute(ReadAllTokenVerificacaoCommand aTokenVerificacaoCommand) {

        return Try(() -> gateway.readAll(aTokenVerificacaoCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllTokenVerificacaoOutput::from);
    }
}
