package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.UpdateEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.UpdateEnderecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoId;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateEnderecoUseCaseImpl extends UpdateEnderecoUseCase {

    private final EnderecoDomainGateway gateway;

    public UpdateEnderecoUseCaseImpl(EnderecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateEnderecoOutput> execute(UpdateEnderecoCommand aEnderecoCommand) {

        Optional<Endereco> aEnderecoDB = aEnderecoCommand.aId() != null ?
                gateway.read(EnderecoId.from(aEnderecoCommand.aId())) : gateway.readByUuid(EnderecoUuid.from(aEnderecoCommand.aUuid()));

        if (aEnderecoDB.isEmpty())
            return Either.left(Notification.create(new Error("Endereco not found: " + (aEnderecoCommand.aId() != null ?
                    aEnderecoCommand.aId() : aEnderecoCommand.aUuid()))));

        var notification = Notification.create();
        var endereco = Endereco.update(aEnderecoDB.get().getId().getValue(),
                                       aEnderecoDB.get().getUuid().getValue(),
                                       aEnderecoCommand.aStatusCode(),
                                       aEnderecoCommand.aUsuarioId(),
                                       aEnderecoCommand.aLogradouro(),
                                       aEnderecoCommand.aNumero(),
                                       aEnderecoCommand.aComplemento(),
                                       aEnderecoCommand.aBairro(),
                                       aEnderecoCommand.aCidade(),
                                       aEnderecoCommand.aEstado(),
                                       aEnderecoCommand.aCep(),
                                       aEnderecoCommand.aPrincipal());
        endereco.validate(notification);

        return notification.hasError() ? Left(notification) : update(endereco);
    }

    @Transactional
    private Either<Notification, UpdateEnderecoOutput> update(Endereco aEndereco) {

        return Try(() -> gateway.update(aEndereco))
                .toEither().bimap(Notification::create, UpdateEnderecoOutput::from);
    }
}
