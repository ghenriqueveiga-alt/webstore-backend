package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.ReadTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.ReadTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadTokenVerificacaoUseCaseImpl extends ReadTokenVerificacaoUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public ReadTokenVerificacaoUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadTokenVerificacaoOutput> execute(ReadTokenVerificacaoCommand aTokenVerificacaoCommand) {

        Optional<TokenVerificacao> aTokenVerificacaoDB = aTokenVerificacaoCommand.aId() != null ?
                gateway.read(TokenVerificacaoId.from(aTokenVerificacaoCommand.aId())) : gateway.readByUuid(TokenVerificacaoUuid.from(aTokenVerificacaoCommand.aUuid()));

        if (aTokenVerificacaoDB.isPresent())
            return Try(aTokenVerificacaoDB::get).toEither().bimap(Notification::create, ReadTokenVerificacaoOutput::from);

        var aTokenVerificacaoId = aTokenVerificacaoCommand.aId() != null ? String.valueOf(aTokenVerificacaoCommand.aId()) : aTokenVerificacaoCommand.aUuid();

        return Either.left(Notification.create(new Error("TokenVerificacao not found: " + aTokenVerificacaoId)));
    }
}
