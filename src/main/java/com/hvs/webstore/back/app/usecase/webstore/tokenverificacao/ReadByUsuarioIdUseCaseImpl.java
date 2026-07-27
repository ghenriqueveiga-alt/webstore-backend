package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.ReadByUsuarioIdCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.ReadAllTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadByUsuarioIdUseCaseImpl extends ReadByUsuarioIdUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public ReadByUsuarioIdUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllTokenVerificacaoOutput> execute(ReadByUsuarioIdCommand aTokenVerificacaoCommand) {

        return Try(() -> gateway.readByUsuarioId(aTokenVerificacaoCommand.aUsuarioId()))
                .toEither().bimap(Notification::create, ReadAllTokenVerificacaoOutput::from);
    }
}
