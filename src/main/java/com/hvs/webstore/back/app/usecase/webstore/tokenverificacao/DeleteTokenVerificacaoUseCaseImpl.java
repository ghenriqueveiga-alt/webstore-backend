package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.DeleteTokenVerificacaoCommand;
import com.hvs.webstore.back.app.output.webstore.tokenverificacao.DeleteTokenVerificacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteTokenVerificacaoUseCaseImpl extends DeleteTokenVerificacaoUseCase {

    private final TokenVerificacaoDomainGateway gateway;

    public DeleteTokenVerificacaoUseCaseImpl(TokenVerificacaoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteTokenVerificacaoOutput> execute(DeleteTokenVerificacaoCommand aTokenVerificacaoCommand) {

        Optional<TokenVerificacao> aTokenVerificacaoDB = aTokenVerificacaoCommand.aId() != null ?
                gateway.read(TokenVerificacaoId.from(aTokenVerificacaoCommand.aId())) : gateway.readByUuid(TokenVerificacaoUuid.from(aTokenVerificacaoCommand.aUuid()));

        if (aTokenVerificacaoDB.isEmpty())
            return Either.left(Notification.create(new Error("TokenVerificacao not found: " + (aTokenVerificacaoCommand.aId() != null ?
                    aTokenVerificacaoCommand.aId() : aTokenVerificacaoCommand.aUuid()))));

        return delete(aTokenVerificacaoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteTokenVerificacaoOutput> delete(TokenVerificacao aTokenVerificacao) {

        return Try(() -> {
            gateway.delete(aTokenVerificacao);
            return DeleteTokenVerificacaoOutput.from(aTokenVerificacao);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
