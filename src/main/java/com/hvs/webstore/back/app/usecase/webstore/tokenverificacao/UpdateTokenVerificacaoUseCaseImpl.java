package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.UpdateTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.UpdateTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateTokenVerificacaoUseCaseImpl extends UpdateTokenVerificacaoUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public UpdateTokenVerificacaoUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateTokenVerificacaoOutput> execute(UpdateTokenVerificacaoCommand aTokenVerificacaoCommand) {

        Optional<TokenVerificacao> aTokenVerificacaoDB = aTokenVerificacaoCommand.aId() != null ?
                gateway.read(TokenVerificacaoId.from(aTokenVerificacaoCommand.aId())) : gateway.readByUuid(TokenVerificacaoUuid.from(aTokenVerificacaoCommand.aUuid()));

        if (aTokenVerificacaoDB.isEmpty())
            return Left(Notification.create(new Error("TokenVerificacao not found: " + (aTokenVerificacaoCommand.aId() != null ?
                    aTokenVerificacaoCommand.aId() : aTokenVerificacaoCommand.aUuid()))));

        var notification = Notification.create();
        var token = TokenVerificacao.update(aTokenVerificacaoDB.get().getId().getValue(),
                                            aTokenVerificacaoDB.get().getUuid().getValue(),
                                            aTokenVerificacaoCommand.aStatusCode(),
                                            aTokenVerificacaoCommand.aUsuarioId(),
                                            aTokenVerificacaoCommand.aToken(),
                                            aTokenVerificacaoCommand.aTipoCode(),
                                            aTokenVerificacaoCommand.aExpiradoEm(),
                                            aTokenVerificacaoCommand.aUtilizadoEm(),
                                            aTokenVerificacaoCommand.aCriadoEm());
        token.validate(notification);

        return notification.hasError() ? Left(notification) : update(token);
    }

    @Transactional
    private Either<Notification, UpdateTokenVerificacaoOutput> update(TokenVerificacao aTokenVerificacao) {

        return Try(() -> gateway.update(aTokenVerificacao))
                .toEither().bimap(Notification::create, UpdateTokenVerificacaoOutput::from);
    }
}
