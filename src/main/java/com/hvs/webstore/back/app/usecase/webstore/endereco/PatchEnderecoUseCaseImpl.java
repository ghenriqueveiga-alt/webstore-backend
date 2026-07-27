package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.PatchEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.PatchEnderecoOutput;
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

public class PatchEnderecoUseCaseImpl extends PatchEnderecoUseCase {

    private final EnderecoDomainGateway gateway;

    public PatchEnderecoUseCaseImpl(EnderecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchEnderecoOutput> execute(PatchEnderecoCommand aEnderecoCommand) {

        Optional<Endereco> aEnderecoDB = aEnderecoCommand.aId() != null ?
                gateway.read(EnderecoId.from(aEnderecoCommand.aId())) : gateway.readByUuid(EnderecoUuid.from(aEnderecoCommand.aUuid()));

        if (aEnderecoDB.isEmpty())
            return Either.left(Notification.create(new Error("Endereco not found: " + (aEnderecoCommand.aId() != null ?
                    aEnderecoCommand.aId() : aEnderecoCommand.aUuid()))));

        var notification = Notification.create();
        var endereco = Endereco.patch(aEnderecoCommand.aStatusCode(),
                                      aEnderecoCommand.aLogradouro(),
                                      aEnderecoCommand.aNumero(),
                                      aEnderecoCommand.aComplemento(),
                                      aEnderecoCommand.aBairro(),
                                      aEnderecoCommand.aCidade(),
                                      aEnderecoCommand.aEstado(),
                                      aEnderecoCommand.aCep(),
                                      aEnderecoCommand.aPrincipal(),
                                      aEnderecoDB.get());
        endereco.validate(notification);

        return notification.hasError() ? Left(notification) : patch(endereco);
    }

    @Transactional
    private Either<Notification, PatchEnderecoOutput> patch(Endereco aEndereco) {

        return Try(() -> gateway.patch(aEndereco))
                .toEither().bimap(Notification::create, PatchEnderecoOutput::from);
    }
}
