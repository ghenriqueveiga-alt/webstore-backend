package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.CreateEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.CreateEnderecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateEnderecoUseCaseImpl extends CreateEnderecoUseCase {

    private final EnderecoDomainGateway gateway;

    public CreateEnderecoUseCaseImpl(EnderecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateEnderecoOutput> execute(CreateEnderecoCommand aEnderecoCommand) {

        var notification = Notification.create();
        var endereco = Endereco.create(aEnderecoCommand.aUsuarioId(),
                                       aEnderecoCommand.aLogradouro(),
                                       aEnderecoCommand.aNumero(),
                                       aEnderecoCommand.aComplemento(),
                                       aEnderecoCommand.aBairro(),
                                       aEnderecoCommand.aCidade(),
                                       aEnderecoCommand.aEstado(),
                                       aEnderecoCommand.aCep(),
                                       aEnderecoCommand.aPrincipal());
        endereco.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(endereco);
    }

    @Transactional
    private Either<Notification, CreateEnderecoOutput> create(Endereco aEndereco) {

        return Try(() -> gateway.create(aEndereco))
                .toEither().bimap(Notification::create, CreateEnderecoOutput::from);
    }
}
